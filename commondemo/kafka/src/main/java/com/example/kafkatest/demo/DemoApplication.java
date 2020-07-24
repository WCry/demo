package com.example.kafkatest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * kafka消费默认从当前已经存在偏移位置消费
 * 默认不会消费Consumer上线前的消息，
 * 需要主动设置offset进行重新消费
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
