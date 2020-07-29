package com.zxp.sso.server;

import com.zxp.sso.WeiboOAuth2AccessTokenResponseClient;
import com.zxp.sso.WeiboOAuth2UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;


@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    @Resource
    private WeiboOAuth2AccessTokenResponseClient weiboOAuth2AccessTokenResponseClient;

    @Resource
    private WeiboOAuth2UserService weiboOAuth2UserService;

    public SsoSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //展示登录界面的URL
                .loginPage("/authentication/require")
                //配置登录处理的URL 在认证中心的登录界面 登录表单的提交地方
                .loginProcessingUrl("/authentication/form")
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        "/authentication/form",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.woff2"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
        //合适位置增加自定义过滤器处理
       // http.addFilterBefore(new BeforeLoginFilter(), ChannelProcessingFilter.class);
        http.oauth2Login().loginPage("/login/weibo")
                .tokenEndpoint().accessTokenResponseClient(weiboOAuth2AccessTokenResponseClient)
                .and()
                .userInfoEndpoint()
                .userService(weiboOAuth2UserService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
