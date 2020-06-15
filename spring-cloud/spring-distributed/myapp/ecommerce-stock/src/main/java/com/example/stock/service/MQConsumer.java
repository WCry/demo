package com.example.stock.service;


import com.example.consulfeignapi.config.RabbitMqConfig;
import com.example.consulfeignapi.dto.StockDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MQConsumer {
    /**
     * 消费库存中的队列消息 直接消费的方式
     * @param stockDto 库存信息
     */
    @RabbitListener(queues = RabbitMqConfig.QUEUE_INFORM_STOCK)
    public void receive(StockDto stockDto) {

        System.out.println("收到消息 : " + stockDto.getGoodsID());
    }
}
