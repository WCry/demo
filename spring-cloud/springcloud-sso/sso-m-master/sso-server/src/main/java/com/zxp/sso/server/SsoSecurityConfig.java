package com.zxp.sso.server;


import com.zxp.sso.mobile.PhoneCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;


@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SsoSecurityConfig(@Qualifier("ssoUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Resource
    private PhoneCodeSecurityConfig messageCodeSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/authentication/require").and().authorizeRequests()
                .antMatchers("/message").permitAll().and().authorizeRequests()
                .antMatchers("/hello").hasAuthority("ROLE_USER");
        http.csrf().disable();

//        http.formLogin()
//                //展示登录界面的URL
//                .loginPage("/authentication/require")
//                //配置登录处理的URL 在认证中心的登录界面 登录表单的提交地方
//                .loginProcessingUrl("/authentication/form")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/authentication/require",
//                        "/authentication/form",
//                        "/**/*.js",
//                        "/**/*.css",
//                        "/**/*.jpg",
//                        "/**/*.png",
//                        "/**/*.woff2"
//                )
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf().disable();
        http.apply(messageCodeSecurityConfig);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
