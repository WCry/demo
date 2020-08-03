package com.zxp.sso.mobile;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * user:zxp
 * Day:2020,07,31
 **/

@Configuration
public class PhoneWebConfig implements WebMvcConfigurer {

    @Resource
    private PhoneCodeFilter messageCodeFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(messageCodeFilter).addPathPatterns("/message/code");
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login/form").setViewName("login");
//    }
}
