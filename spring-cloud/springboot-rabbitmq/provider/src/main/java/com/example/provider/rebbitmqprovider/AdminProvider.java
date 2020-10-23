//package com.example.provider.rebbitmqprovider;
//
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author zhangxuepei
// * @since 3.0
// * 声明一个直接交换的方式
// * 直接当时路由key和队列是完全匹配的
// */
//@Component
//public class AdminProvider {
//
//    @Autowired
//    AdminProvider(AmqpAdmin amqpAdmin) {
//        amqpAdmin.declareExchange(new DirectExchange("direct.exchange"));
//        amqpAdmin.declareQueue(new Queue("direct.queue.a", true));
//        amqpAdmin.declareQueue(new Queue("direct.queue.b", true));
//        amqpAdmin.declareBinding(new Binding("direct.queue.a",
//                Binding.DestinationType.QUEUE, "direct.exchange", "a", null));
//        amqpAdmin.declareBinding(new Binding("direct.queue.b",
//                Binding.DestinationType.QUEUE, "direct.exchange", "b", null));
//    }
//}
