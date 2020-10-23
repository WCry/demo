package com.example.provider.rebbitmqprovider;

import com.example.provider.constant.RabbitPropertiesConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 *    死信是指消息发生意外无法进行处理，然后你怎么去处理
 *    死信队列用来记录消费出现异常的消息，原队列是属于正常消费
 *    重定向队列用于进行消费异常消息
 *   死信队列首先根据路由key进行消费
 *   如果出现错误，然后重定向到新的队列
 */
@Component
public class RabbitMqDeadLetterQueueConfig {
    /**
     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
     */
    @Bean("deadLetterExchange")
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange(RabbitPropertiesConstant.DEAD_LETTER_EXCHANGE).durable(true).build();
    }

    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>(2);
        //       x-dead-letter-exchange    声明  死信队列Exchange
        args.put("x-dead-letter-exchange", RabbitPropertiesConstant.DEAD_LETTER_EXCHANGE);
        //       x-dead-letter-routing-key    声明 死信队列抛出异常重定向队列的routingKey(T_KEY_R)
        args.put("x-dead-letter-routing-key", RabbitPropertiesConstant.DEAD_LETTER_REDIRECT_ROUTING_KEY);
        return QueueBuilder.durable(RabbitPropertiesConstant.DEAD_LETTER_QUEUE).withArguments(args).build();
    }

    @Bean("redirectQueue")
    public Queue redirectQueue() {
        return QueueBuilder.durable(RabbitPropertiesConstant.REDIRECT_QUEUE).build();
    }

    /**
     * 死信队列绑定到死信交换器上.
     *
     * @return the binding
     */
    @Bean
    public Binding deadLetterBinding() {
        return new Binding(RabbitPropertiesConstant.DEAD_LETTER_QUEUE, Binding.DestinationType.QUEUE, RabbitPropertiesConstant.DEAD_LETTER_EXCHANGE,
                RabbitPropertiesConstant.DEAD_LETTER_ROUTING_KEY, null);

    }

    /**
     * 将重定向队列通过routingKey(T_KEY_R)绑定到死信队列的Exchange上
     *
     * @return the binding
     */
    @Bean
    public Binding redirectBinding() {
        return new Binding(RabbitPropertiesConstant.REDIRECT_QUEUE, Binding.DestinationType.QUEUE, RabbitPropertiesConstant.DEAD_LETTER_EXCHANGE, RabbitPropertiesConstant.DEAD_LETTER_REDIRECT_ROUTING_KEY, null);
    }
}
