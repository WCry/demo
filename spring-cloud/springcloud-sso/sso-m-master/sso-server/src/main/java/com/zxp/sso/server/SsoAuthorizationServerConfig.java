package com.zxp.sso.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 客户端一些配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       //配置客户客户端的详细信息
        clients.inMemory()
                .withClient("clinet1")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("all","read","write").redirectUris("http://localhost:8084/client2/login")
                .autoApprove(true)
                .and()
                //配置一个 允许接入sso验证中心的客户端的客户端ID和客户端的密码
                .withClient("client2")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                //设置客户端的授权方式 授权码的方式和书信tkoen的方式
                .authorizedGrantTypes("authorization_code", "refresh_token")
                //这里配置的redirectUris("http://localhost:8084/client2/login")
                //是该sso登录客户端的反向回调地址 反向回调地址主要是用来接受授权码，
                //客户端更具授权 获取access的token验证的密钥，
                //这里地址 默认的地址是是OAuth2的默认地址是login，可以在客户端中进行设定。
                //不过这里回调地址和设置回调地址需要一致
                .scopes("all","read","write").redirectUris("http://localhost:8084/client2/login")
                .autoApprove(true);
    }

    /**
     * 配置jwttokenStore  配置认证服务的jTWT的存储
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
    }

    /**
     * springSecurity 授权表达式，访问tokenkey时需要经过认证
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");
    }

    /**
     * JWTtokenStore
     * @return
     */
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 生成JTW token
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("clent");
        return converter;
    }
}
