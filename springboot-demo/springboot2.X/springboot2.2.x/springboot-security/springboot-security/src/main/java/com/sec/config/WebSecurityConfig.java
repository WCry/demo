package com.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * user:zxp
 * Day:2020,04,19
 **/
@Configuration
public class WebSecurityConfig extends WebMvcConfigurationSupport {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(new TokenInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/403");
        addInterceptor.excludePathPatterns("/toLogin");
        addInterceptor.excludePathPatterns("/login**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
}

