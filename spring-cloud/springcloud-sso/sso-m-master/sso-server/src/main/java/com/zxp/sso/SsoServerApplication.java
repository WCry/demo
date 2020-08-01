package com.zxp.sso;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;

//https://blog.csdn.net/weixin_43931625/article/details/105944727
//
@SpringBootApplication
public class SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
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
