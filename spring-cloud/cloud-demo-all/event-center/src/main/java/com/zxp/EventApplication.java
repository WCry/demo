package com.zxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 事件中心的处理主要降低消息中间件对于系统的耦合
 * 和有自定义的事件的处理，避免因为消息中间件的变换引起系统崩溃
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EventApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }
}
