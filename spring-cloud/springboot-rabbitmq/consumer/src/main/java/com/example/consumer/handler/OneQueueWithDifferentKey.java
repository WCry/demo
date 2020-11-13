package com.example.consumer.handler;

/**
 * 不同的路由key绑定到同一个队列上
 *
 * @since 3.0
 */

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class OneQueueWithDifferentKey {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "TOPIC-A", durable = "true", autoDelete = "false"), exchange = @Exchange(value = "TOPIC-EXCHANGE", type = ExchangeTypes.TOPIC), key = "A"))
    @RabbitHandler
    public void handlerA(@Payload String payload) {
        System.out.println("监听A的消费者收到来自ABQueue的消息：" + payload);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "TOPIC-B", durable = "true", autoDelete = "false"), exchange = @Exchange(value = "TOPIC-EXCHANGE-2", type = ExchangeTypes.TOPIC), key = "B"))
    @RabbitHandler
    public void handlerB(@Payload String payload) {

        System.out.println("监听A的消费者收到来自ABQueue的消息：" + payload);
    }
}
