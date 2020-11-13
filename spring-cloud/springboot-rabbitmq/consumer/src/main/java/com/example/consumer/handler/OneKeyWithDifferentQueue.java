package com.example.consumer.handler;

/**
 * @since 3.0
 */

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 不同的路由key绑定到同一个队列上
 */
@Component
public class OneKeyWithDifferentQueue {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "TOPIC-A-1", durable = "true", autoDelete = "false"), exchange = @Exchange(value = "TOPIC-EXCHANGE", type = ExchangeTypes.TOPIC), key = "A.*"))
    @RabbitHandler
    public void handlerA(@Payload String payload) {
        System.out.println("监听TOPIC-A-1的消费者收的消息：" + payload);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "TOPIC-A-2", durable = "true", autoDelete = "false"), exchange = @Exchange(value = "TOPIC-EXCHANGE", type = ExchangeTypes.TOPIC), key = "A.*"))
    @RabbitHandler
    public void handlerB(@Payload String payload) {
        System.out.println("监听TOPIC-A-2的消费者收到消息：" + payload);
    }
}
