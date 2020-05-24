package com.gf.config;

import com.gf.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * 认证服务配置适配器
 * 开启任务服务
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    /*** 注入权限验证控制器 来支持 password grant type*/
    private final AuthenticationManager authenticationManager;
    /*** 注入userDetailsService，开启refresh_token需要用到*/
    private final MyUserDetailsService userDetailsService;
    /*** 设置保存token的方式，一共有五种，这里采用数据库的方式*/
    private final TokenStore tokenStore;
    private final DataSource dataSource;
    //异常返回解析
    private final WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Autowired
    public AuthorizationServerConfiguration(AuthenticationManager authenticationManager,
                                            MyUserDetailsService userDetailsService,
                                            DataSource dataSource,
                                            MyTokenStore tokenStore,
                                            WebResponseExceptionTranslator webResponseExceptionTranslator) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenStore = tokenStore;
        this.webResponseExceptionTranslator = webResponseExceptionTranslator;
        this.dataSource = dataSource;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //配置
        security.tokenKeyAccess("permitAll()")
                //配置token验证的通道denyAll()拒绝,isAuthenticated()已经认证的
                .checkTokenAccess("permitAll()")
                //允许客户端进行表单验证
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置Client的数据源
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //开启密码授权类型
        endpoints.authenticationManager(authenticationManager);
        //配置token存储方式
        endpoints.tokenStore(tokenStore);
        //自定义登录或者鉴权失败时的返回信息
        endpoints.exceptionTranslator(webResponseExceptionTranslator);
        //要使用refresh_token的话，需要额外配置userDetailsService
        endpoints.userDetailsService(userDetailsService);
    }
}
