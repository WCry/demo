package com.crhms.security.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration

public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthServerConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception { // @formatter:off
        clients.inMemory()
                //Client
                .withClient("sampleClientId")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("implicit", "authorization_code","client_credentials")
                .scopes("read", "write", "foo", "bar","webclient")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600)
                .redirectUris("http://localhost:9001/login")
                .and()
                //Client2
                .withClient("sampleClientId2")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("implicit", "authorization_code","client_credentials")
                .scopes("read", "write", "foo", "bar","webclient")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600)
                .redirectUris("http://localhost:9000/login")
                .and()
                //resource-server
                .withClient("resourceserver")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("implicit", "authorization_code","client_credentials")
                .scopes("read", "write", "foo", "bar","webclient")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600)
                .redirectUris("http://localhost:9000/login");
    } // @formatter:on

//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        return defaultTokenServices;
//    }
    /**
     * 配置jwttokenStore  配置认证服务的jTWT的存储
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
    }

//    @Override
//    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.tokenKeyAccess("permitAll()").allowFormAuthenticationForClients().checkTokenAccess("isAuthenticated()");
//    }
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



//    @Override
//    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
////        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
////        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter()));
//        endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain).authenticationManager(authenticationManager);
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("123");
//        return converter;
//    }
}
