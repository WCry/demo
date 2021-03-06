package com.example.provider;

import com.example.provider.event.RabbitMqApplicationEvent;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.retry.RetryListener;
import org.springframework.retry.support.RetryTemplate;

/**
 * 自动配置
 * 1. RabbitAutoConfiguration
 * 2. 自动配置了连接工厂ConnectionFactory
 * 3. RabbitProperties 封装了RabbitMQ的配置
 * 4. RabbitTemplate : 给RabbitMQ发送和接受消息
 * 5. AmqpAdmin : RabbitMQ系统管理功能组件
 * 6. @EnableRabbit + @RabbitListener
 * Rabbitmq一个队列只能有一个消费者进行消费，
 *  如果一个队列被多个消费同时绑定，将会在消费者之间共同消费
 *  谁消费的快，谁处理的多
 *
 *  一个队列一个线程去处理
 *
 *  RabbitMq使用
 *  https://www.jianshu.com/p/090ed51006d5
 */
//@EnableRabbit
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
