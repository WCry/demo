package com.example.consulfeignconsumer;

import feign.Logger;
import feign.RequestInterceptor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangxuepei
 * @since 3.0
 */

public class FeignInterceptorConfig {
    public static ThreadLocal<String> dsad=new ThreadLocal<>();
    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        RequestInterceptor requestInterceptor = template -> {
            System.out.println(template.method());
            dsad.set(template.url());
            System.out.println(template.feignTarget().url());
            //传递日志traceId
            String traceId = MDC.get("traceId");
            template.header("traceId", traceId);
        };
        return requestInterceptor;
    }

    //    @Bean
    //    Logger FeignLog() {
    //        return new FeignLogger();
    //    }
}
