package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 微服务网关
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableWebFluxSecurity
public class ControllerGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControllerGatewayApplication.class, args);
    }
}
