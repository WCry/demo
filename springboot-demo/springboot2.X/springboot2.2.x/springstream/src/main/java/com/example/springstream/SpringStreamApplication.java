package com.example.springstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Stream 构建微服务之间的流式处理
 * https://docs.spring.io/spring-cloud-stream-binder-rabbit/docs/3.1.0/reference/html/spring-cloud-stream-binder-rabbit
 * .html#_configuration_options
 */
@SpringBootApplication
public class SpringStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStreamApplication.class, args);
    }

}
