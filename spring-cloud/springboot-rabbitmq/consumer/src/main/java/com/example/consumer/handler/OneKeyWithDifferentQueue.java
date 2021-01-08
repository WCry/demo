package com.example.consumer.handler;

/**
 * @since 3.0
 */

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 不同的路由key绑定到同一个队列上
 */
@Component
public class OneKeyWithDifferentQueue {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "TOPIC-A-1", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "TOPIC-EXCHANGE", type = ExchangeTypes.TOPIC), key = "A.*"))
    public void handlerA(@Payload String payload, Message message,Channel channel) throws InterruptedException, IOException {
        System.out.println(message.getMessageProperties().getDeliveryTag());
        System.out.println("监听TOPIC-A-1的消费者收的消息：" + payload);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "TOPIC-A-1", durable = "true",
            autoDelete = "false"), exchange = @Exchange(value = "TOPIC-EXCHANGE", type = ExchangeTypes.TOPIC), key = "A.C.*"))
    public void handlerAC(@Payload String payload, Message message, Channel channel) throws IOException {
        System.out.println("监听TOPIC-A-1的消费者收的消息：");
        System.out.println(message.getMessageProperties().getDeliveryTag());
        System.out.println("被拒绝的消息：" + payload);
        channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "TOPIC-A-2", durable = "true", autoDelete = "false"), exchange = @Exchange(value = "TOPIC-EXCHANGE", type = ExchangeTypes.TOPIC), key = "A.*"))
    public void handlerB(@Payload String payload,Message message,Channel channel) throws IOException {
        //System.out.println("监听TOPIC-A-2的消费者收到消息：" + payload);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
