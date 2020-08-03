package com.example.consumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Slf4j
@Component
@RabbitListener(queuesToDeclare = {@Queue(name=RabbitPropertiesConstant.DEAD_LETTER_QUEUE,
        arguments={@Argument(name = "x-dead-letter-exchange",value = RabbitPropertiesConstant.DEAD_LETTER_EXCHANGE),
        @Argument(name="x-dead-letter-routing-key",value = RabbitPropertiesConstant.DEAD_LETTER_REDIRECT_ROUTING_KEY)})})
public class DeadLetterConsumer {

    /*@RabbitListener(bindings = @QueueBinding(
                  value = @Queue(value = RabbitDeadLetterConfig.DEAD_LETTER_QUEUE, durable = "true"),
                  exchange = @Exchange(value = RabbitDeadLetterConfig.DEAD_LETTER_EXCHANGE, type = ExchangeTypes.DIRECT),
                  key = RabbitDeadLetterConfig.DEAD_LETTER_TEST_ROUTING_KEY)
          )*/
    @RabbitHandler
    public void testDeadLetterQueueAndThrowsException(@Payload Integer number, Channel channel) {
        log.warn("DeadLetterConsumer :{}/0 ", number);
        //除以0 出现异常
        int i = number / 0;
    }
}
