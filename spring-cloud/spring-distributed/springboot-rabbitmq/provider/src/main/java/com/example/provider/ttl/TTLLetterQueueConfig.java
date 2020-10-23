//package com.example.provider.ttl;
//
//import com.example.provider.RabbitPropertiesConstant;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.Exchange;
//import org.springframework.amqp.core.ExchangeBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.QueueBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author zhangxuepei
// * @since 3.0
// *    死信是指消息发生意外无法进行处理，然后你怎么去处理
// *    死信队列用来记录消费出现异常的消息，原队列是属于正常消费
// *    重定向队列用于进行消费异常消息
// *   死信队列首先根据路由key进行消费
// *   如果出现错误，然后重定向到新的队列
// */
//@Configuration
//public class TTLLetterQueueConfig {
//    /**
//     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
//     */
//    @Bean
//    public Exchange ttlLetterExchange() {
//        return ExchangeBuilder.directExchange(RabbitPropertiesConstant.TTL_LETTER_EXCHANGE).durable(true).build();
//    }
//    @Bean
//    public Queue ttlLetterQueue() {
//        Map<String, Object> args = new HashMap<>(2);
//        //       x-dead-letter-exchange    声明  死信队列Exchange
//        args.put("x-dead-letter-exchange",
//                RabbitPropertiesConstant.TTL_LETTER_EXCHANGE);
//        //       x-dead-letter-routing-key    声明 死信队列抛出异常重定向队列的routingKey(T_KEY_R)
//        args.put("x-dead-letter-routing-key", RabbitPropertiesConstant.TTL_LETTER_REDIRECT_ROUTING_KEY);
//        args.put("x-message-ttl", RabbitPropertiesConstant.TTL);
//        return QueueBuilder.durable(RabbitPropertiesConstant.TTL_LETTER_QUEUE).withArguments(args).build();
//    }
//
//    public Queue ttlRedirectQueue() {
//        return QueueBuilder.durable(RabbitPropertiesConstant.TTL_REDIRECT_QUEUE).build();
//    }
//
//    /**
//     * 死信队列绑定到死信交换器上.
//     *
//     * @return the binding
//     */
//    @Bean
//    public Binding ttlLetterBinding() {
//        return new Binding(RabbitPropertiesConstant.TTL_LETTER_QUEUE,
//                Binding.DestinationType.QUEUE,
//                RabbitPropertiesConstant.TTL_LETTER_EXCHANGE,
//                RabbitPropertiesConstant.TTL_LETTER_ROUTING_KEY, null);
//
//    }
//
//    /**
//     *
//     * @return the binding
//     */
//    @Bean
//    public Binding ttlRedirectBinding() {
//        return new Binding(RabbitPropertiesConstant.TTL_REDIRECT_QUEUE,
//                Binding.DestinationType.QUEUE,
//                RabbitPropertiesConstant.TTL_LETTER_EXCHANGE,
//                RabbitPropertiesConstant.TTL_LETTER_REDIRECT_ROUTING_KEY, null);
//    }
//}
