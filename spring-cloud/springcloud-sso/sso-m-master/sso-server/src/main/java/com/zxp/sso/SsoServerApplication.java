package com.zxp.sso;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 */
@SpringBootApplication
public class SsoServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }
    @Bean
    String ddd() {
        return new String("aaa");
    }
    /**
     * 为测试环境添加相关的 Request Dumper information，便于调试
     * @return
     */
    @Profile("!cloud")
    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }
}
