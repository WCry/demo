//package com.example.provider.rebbitmqprovider;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.HeadersExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.beans.factory.annotation.Qualifier;
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
// */
//@Configuration
//public class HeaderQueueConfig {
//    @Bean(name = "header.queue")
//    public Queue headers_queue() {
//        return new Queue("header.queue");
//    }
//
//    @Bean
//    public HeadersExchange exchange() {
//        return new HeadersExchange("header.exchange");
//    }
//
//    @Bean
//    Binding bindingExchangeTopicQueue2(@Qualifier("header.queue") Queue queue, HeadersExchange exchange) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("one", "a");
//        map.put("two", "b");
//        //whereAll表示全部匹配
//        //return BindingBuilder.bind(queue).to(headersExchange).whereAll(map).match();
//        //whereAny表示部分匹配
//        return BindingBuilder.bind(queue).to(exchange).whereAny(map).match();
//    }
//
//}
