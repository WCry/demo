package com.example.consulfeignconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulFeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulFeignConsumerApplication.class, args);
    }

}
