package com.example.order.service.impl;

import com.example.consulfeignapi.dto.StockDto;
import com.example.order.dto.OrderDto;
import com.example.order.service.OrderServer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.BeanUtils;
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
        StockDto stockDto=new StockDto();
        stockDto.setGoodsID(UUID.randomUUID().toString());
        //发送消息到队列当中 为什么有消息 队列 因为产生消息的速度比消费消息的速度快，无法及时的处理
        //所以对于订单和库存的扣减的 如果是内部的系统可以不用消息对队列
        amqpTemplate.convertSendAndReceive("stock.exchange",  "stock", stockDto);
        return true;
    }

    @Override
    public Boolean newOrder(OrderDto orderDto) {
        return null;
    }

    private void test(OrderDto orderDto) {
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
        amqpTemplate.convertAndSend("stock.exchange",  "stock", map);
    }
}
