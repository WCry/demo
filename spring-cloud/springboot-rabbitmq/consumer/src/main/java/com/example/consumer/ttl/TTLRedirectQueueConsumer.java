package com.example.consumer.ttl;

import com.example.consumer.handler.RabbitPropertiesConstant;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RabbitListener(queuesToDeclare = {@Queue(RabbitPropertiesConstant.TTL_REDIRECT_QUEUE)})
@Component
@Slf4j
public class TTLRedirectQueueConsumer {

    /**
     * @param number
     */
    @RabbitHandler
    public void fromDeadLetter(Integer number) {
        log.warn("延时1秒消费 : {}", number);
        //这里可以对于死信队列进行消息记录
        // 对应的操作
        int i = number / 1;
    }
}
