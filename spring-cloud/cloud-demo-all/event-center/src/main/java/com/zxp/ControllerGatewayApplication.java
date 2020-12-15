package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/**
 * 自身应用调用网关
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ControllerGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControllerGatewayApplication.class, args);
    }
}
