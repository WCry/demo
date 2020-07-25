package com.example.kafkatest.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * kafka消费默认从当前已经存在偏移位置消费
 * 默认不会消费Consumer上线前的消息，
 * 需要主动设置offset进行重新消费
 * Kafka的assign和subscribe订阅模式和手动提交偏移量
 * subscribe:group起作用
 * assign:手动绑定消费分区方式，Group设置不起作用
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
