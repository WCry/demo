package com.example.consumer.handler;

/**
 * 不同的路由key绑定到同一个队列上
 *
 * @since 3.0
 */

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.ApplicationEvent;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;


@Component
public class OneQueueWithDifferentKey {


    @Resource
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
    private Boolean isStop = false;


    public OneQueueWithDifferentKey() {

    }

    @RabbitListener(id = "aaa", bindings = @QueueBinding(value = @Queue(value = "TOPIC-A", durable = "true",
            autoDelete = "false"), exchange = @Exchange(value = "TOPIC-EXCHANGE", type = ExchangeTypes.TOPIC), key = "A"))
    public void handlerA(Channel channel, Message message, @Payload Byte[] payload) throws IOException {
        try {
            System.out.println("监听A的消费者收到来自AQueue的消息：" + payload);
            if (!isStop) {

                SimpleMessageListenerContainer messageListenerContainer = (SimpleMessageListenerContainer) rabbitListenerEndpointRegistry.getListenerContainer("aaa");
                messageListenerContainer.setConcurrentConsumers(0);
               // messageListenerContainer.setPrefetchCount(5);
              //  messageListenerContainer.removeQueueNames("TOPIC-A");
//                messageListenerContainer.setForceCloseChannel(false);
//                messageListenerContainer.stop();
                isStop = true;
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "TOPIC-A", durable = "true",
    //            autoDelete = "false"), exchange = @Exchange(value = "TOPIC-EXCHANGE", type = ExchangeTypes.TOPIC), key = "A"))
    //    @RabbitHandler
    //    public void handlerA(@Payload String payload) {
    //        System.out.println("监听A的消费者收到来自AQueue的消息：" + payload);
    //    }


    //    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "TOPIC-B", durable = "true", autoDelete = "false"),
    //            exchange = @Exchange(value = "TOPIC-EXCHANGE-2", type = ExchangeTypes.TOPIC), key = "B"))
    //    @RabbitHandler
    //    public void handlerB(@Payload String payload) {
    //
    //        System.out.println("监听A的消费者收到来自ABQueue的消息：" + payload);
    //    }
}
