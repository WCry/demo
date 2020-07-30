package com.example.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConsumer {
    /**
     * Consumer启动时候需要
     * 必须绑定到一个已经存在的队列上面
     * 类上面可以有两个RabbitListener
     * 一个配置声明队列
     * queuesToDeclare可以强制声明一个队列，不存在生成队列为绑定交换器，需要有生产者绑定
     * 直接字符串无法声明一个队列，只能简单消费队列
     * @param message
     */
    @RabbitListener(queues = "topic.queue.a")
    public void topicQueueAMessage(Message message) {
        System.out.println("收到来自于主题交换A队列消息:");
        System.out.println(new String(message.getBody()));
    }

    @RabbitListener(queues = "topic.queue.b")
    //@SendTo(value="topic.queue.b")
    public void processMessage(Message message) {
        System.out.println("收到来自于主题交换B队列消息:");
        System.out.println(new String(message.getBody()));
    }


    @RabbitListener(queues = "direct.queue.a")
    public void directAQueueConsumer(Message message) {
        System.out.println("收到来自于直接交换A队列消息:");
        System.out.println(new String(message.getBody()));
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "direct.queue.b"))
    @SendTo(value="direct.queue.b")
    public void directBQueueConsumer(Message message) {
        System.out.println("收到来自于直接交换B队列消息:");
        System.out.println(new String(message.getBody()));
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "fanout.A"))
    public void fanoutAQueueConsumer(Message message) {
        System.out.println("收到来自于扇出A交换队列消息:");
        System.out.println(new String(message.getBody()));
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "fanout.B"))
    public void fanoutBQueueConsumer(Message message) {
        System.out.println("收到来自于扇出B交换队列消息:");
        System.out.println(new String(message.getBody()));
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "fanout.C"))
    public void fanoutCQueueConsumer(Message message) {
        System.out.println("收到来自于扇出C交换队列消息:");
        System.out.println(new String(message.getBody()));
    }
    //    @RabbitListener(queuesToDeclare = @Queue(value = "header.queue"))
    //    public void HeaderQueueConsumer(@ Payload Message message,@Header("header") String header) {
    //        System.out.println("收到来自于Header交换队列消息:");
    //        System.out.println(new String(message.getBody()));
    //    }

}


