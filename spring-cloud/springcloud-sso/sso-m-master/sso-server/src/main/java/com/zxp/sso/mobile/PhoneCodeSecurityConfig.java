package com.zxp.sso.mobile;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * user:zxp
 * Day:2020,07,31
 **/
@Configuration
public class PhoneCodeSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        @Resource
        private PhoneCodeAuthenticationProvider messageCodeAuthenticationProvider;

        @Resource
        private PhoneCodeAuthenticationFailureHandler messageCodeAuthenticationFailureHandler;

        @Override
        public void configure(HttpSecurity builder) throws Exception {
            PhoneCodeAuthenticationFilter messageCodeAuthenticationFilter=new PhoneCodeAuthenticationFilter();
            messageCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
            messageCodeAuthenticationFilter.setAuthenticationFailureHandler(messageCodeAuthenticationFailureHandler);

            builder.authenticationProvider(messageCodeAuthenticationProvider)
                    .addFilterAfter(messageCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
