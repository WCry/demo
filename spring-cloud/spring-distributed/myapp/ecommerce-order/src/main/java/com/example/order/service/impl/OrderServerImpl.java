package com.example.order.service.impl;

import com.example.order.dto.OrderDto;
import com.example.order.service.OrderServer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * user:zxp
 * Day:2020,06,14
 **/
public class OrderServerImpl implements OrderServer {
    /**
     * Template 采用才设置 可以支持多种MQ消息队列
     * 高级消息队列模型
     */
    @Autowired
    private  AmqpTemplate amqpTemplate;
    @Override
    public Boolean finishOrder(OrderDto orderDto) {
        //首先转换类到Message，然后调用Send发出信息，Receive收到消息确认之后在进行下一步操作
       // rabbitMessagingTemplate.convertSendAndReceive()
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        amqpTemplate.convertAndSend("TestDirectExchange",  "TestDirectRouting", map);
        return null;
    }

    @Override
    public Boolean newOrder(OrderDto orderDto) {
        return null;
    }
}
