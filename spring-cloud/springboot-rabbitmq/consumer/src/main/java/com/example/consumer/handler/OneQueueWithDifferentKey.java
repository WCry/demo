package com.example.consumer.handler;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @auth tangjianghua
 * @date 2020/8/18
 */
@Component
public class OneQueueWithDifferentKey {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "ABQueue", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "BDC-TOPIC-EXCHANEGE", type = ExchangeTypes.TOPIC),
            key = "A")
    )
    @RabbitHandler
    public void handlerA(@Payload String payload) {

        System.out.println("监听A的消费者收到来自ABQueue的消息：" + payload);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "ABQueue", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "BDC-TOPIC-EXCHANEGE", type = ExchangeTypes.TOPIC),
            key = "B")
    )
    @RabbitHandler
    public void handlerB(@Payload String payload) {

        System.out.println("监听A的消费者收到来自ABQueue的消息：" + payload);
    }
}
