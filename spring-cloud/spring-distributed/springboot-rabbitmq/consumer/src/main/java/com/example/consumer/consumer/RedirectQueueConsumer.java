package com.example.consumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RabbitListener(queuesToDeclare = {@Queue(RabbitPropertiesConstant.REDIRECT_QUEUE)})
@Component
@Slf4j
public class RedirectQueueConsumer {

    /**
     * 重定向队列和死信队列形参一致Integer number
     *
     * @param number
     */
    @RabbitHandler
    public void fromDeadLetter(Integer number) {
        log.warn("RedirectQueueConsumer : {}", number);
        // 对应的操作
        int i = number / 1;
    }
}
