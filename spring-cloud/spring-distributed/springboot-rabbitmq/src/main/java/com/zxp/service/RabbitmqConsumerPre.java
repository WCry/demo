package com.zxp.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqConsumerPre {
    /**
     * Consumer启动时候需要
     * 必须绑定到一个已经存在的队列上面
     * 类上面可以有两个RabbitListener
     * 一个配置声明队列
     * queuesToDeclare可以强制声明一个队列，不存在生成队列为绑定交换器，需要有生产者绑定
     * 直接字符串无法声明一个队列，只能简单消费队列
     * @Payload 消息体
     * @Header
     *
     * @param message
     */
    @RabbitListener(queues = "topic.queue.a")
    public void topicQueueAMessage(Message message) {
        System.out.println("收到来自于主题交换A队列消息:");
        System.out.println(new String(message.getBody()));
    }
}


