package com.request.analysis.demo;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 注册拦截器
 */
@Configuration
public class MvcInterceptorConfig extends WebMvcConfigurationSupport{

    @Autowired
    private MyInterceptor1 myInterceptor1;
    @Autowired
    private MyInterceptor2 myInterceptor2;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(myInterceptor1).addPathPatterns("/**")
                .excludePathPatterns("/stuInfo/getAllStuInfoA","/account/register");
        registry.addInterceptor(myInterceptor2).addPathPatterns("/**");
        //registry.addWebRequestInterceptor();
        super.addInterceptors(registry);
    }
}
