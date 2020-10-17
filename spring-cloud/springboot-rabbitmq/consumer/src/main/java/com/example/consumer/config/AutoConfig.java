//package com.example.consumer.consumer;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageListener;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
//import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
//import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author zhangxuepei
// * @since 3.0
// * Springboot  启动RabbitMQ的位置 自动注入注入了一个
// * RabbitListenerConfigurationSelector
// *
// * rabbitListenerEndpointRegistry
// */
//@Configuration
//public class AutoConfig {
//
//
//    AutoConfig(RabbitListenerContainerFactory rabbitListenerContainerFactory) {
//        RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar=
//                new RabbitListenerEndpointRegistrar();
//        SimpleRabbitListenerEndpoint rabbitListenerEndpoint = new SimpleRabbitListenerEndpoint();
//        rabbitListenerEndpoint.setId("ddddd");
//        rabbitListenerEndpoint.setQueueNames("direct.queue.a");
//        rabbitListenerEndpoint.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                System.out.println("收到消息");
//            }
//        });
//        RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry= new RabbitListenerEndpointRegistry();
//        rabbitListenerEndpointRegistry. registerListenerContainer(rabbitListenerEndpoint,
//                rabbitListenerContainerFactory,true);
//        rabbitListenerEndpointRegistrar.setEndpointRegistry(rabbitListenerEndpointRegistry);
//        rabbitListenerEndpointRegistrar.getEndpointRegistry()
//         .getListenerContainer("ddddd").start();
//    }
//}
