package com.github.hellxz.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 授权服务器 入口
 *
 * @author hellxz
 */
@SpringBootApplication
public class JwtAuthorizationServerApp {

    public static void main(String[] args) {
        SpringApplication.run(JwtAuthorizationServerApp.class, args);
    }
}
