package com.example.consumer.consumer.dead;

import com.example.consumer.consumer.RabbitPropertiesConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;



/**
 * @author zhangxuepei
 * @since 3.0
 */
@Slf4j
@Component
@RabbitListener(returnExceptions = "true",queuesToDeclare = {@Queue(name= RabbitPropertiesConstant.DEAD_LETTER_QUEUE,
        arguments={@Argument(name = "x-dead-letter-exchange",value = RabbitPropertiesConstant.DEAD_LETTER_EXCHANGE),
        @Argument(name="x-dead-letter-routing-key",value = RabbitPropertiesConstant.DEAD_LETTER_REDIRECT_ROUTING_KEY)})})
public class DeadLetterConsumer {

    @RabbitHandler
    public void testDeadLetterQueueAndThrowsException(@Payload Integer number) {
        log.warn("DeadLetterConsumer :{}/0 ", number);
        //除以0 出现异常
        int i = number / 0;
    }
}
