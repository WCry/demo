package com.example.stock.service;


import com.example.consulfeignapi.config.RabbitMqConfig;
import com.example.consulfeignapi.dto.StockDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Component
public class MQConsumer {
    /**
     * 消费库存中的队列消息 直接消费的方式
     * @param stockDto 库存信息
     * https://www.jianshu.com/p/5585b1c65a45
     */
    @RabbitListener(@Exchange)
    @RabbitListener(queues = RabbitMqConfig.QUEUE_INFORM_STOCK)
    public void receive(StockDto stockDto) {
        System.out.println("收到消息 : " + stockDto.getGoodsID());
    }

//    @RabbitListener(queues = RabbitMqConfig.QUEUE_INFORM_STOCK)
//    public void receive(Message message) {
//        System.out.println("收到消息 : ");
//    }
}
