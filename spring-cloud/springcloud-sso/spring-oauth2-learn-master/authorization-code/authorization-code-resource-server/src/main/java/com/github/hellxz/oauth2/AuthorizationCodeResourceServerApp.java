package com.github.hellxz.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 资源服务器 入口
 *
 * @author hellxz
 */
@SpringBootApplication
public class AuthorizationCodeResourceServerApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationCodeResourceServerApp.class, args);
    }
}
