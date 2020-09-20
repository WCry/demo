package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 自身应用调用网关
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableOAuth2Sso
public class ControllerGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControllerGatewayApplication.class, args);
    }
}
