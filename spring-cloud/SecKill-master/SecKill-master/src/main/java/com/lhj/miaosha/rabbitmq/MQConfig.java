package com.lhj.miaosha.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：LHJ
 * @date ：2019/7/30 16:46
 * @description：${description}
 */
@Configuration
public class MQConfig {
    public static final String MIAOSHA_QUEUE = "miaosha.queue";
    public static final String QUEUE = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String ROUTING_KEY1 = "topic.key1";
    public static final String ROUTING_KEY2 = "topic.#";       //#代表0个或多个字符
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String HEADERS_EXCHANGE = "headsExchange";
    public static final String HEADERS_QUEUE = "headersQueue";

    @Bean
    public Queue queue() {
        return new Queue(MIAOSHA_QUEUE, true);
    }
//    /**
//     * Exchange Type : Direct
//     * Routing Key==Binding Key
//     * @param
//     */
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE, true);
//    }
//
//    /**
//     * Exchange Type : topic
//     * Routing Key与Binding Key模糊匹配
//     * @param
//     */
//    @Bean
//    public Queue topicQueue1() {
//        return new Queue(TOPIC_QUEUE1, true);
//    }
//    @Bean
//    public Queue topicQueue2() {
//        return new Queue(TOPIC_QUEUE2, true);
//    }
//    /**
//     * Topic Exchange
//     */
//    @Bean
//    public TopicExchange topicExchange(){
//        return new TopicExchange(TOPIC_EXCHANGE);
//    }
//    /**
//     * Binding
//     * 在绑定（Binding）Exchange和Queue的同时，一般会指定一个Binding Key
//     * topic binding 中 binding key中可以存在两种特殊字符“”与“#”，
//     * 用于做模糊匹配，其中“”用于匹配一个单词，“#”用于匹配多个单词（可以是零个）
//     */
//    @Bean
//    public Binding topicBingding1(){
//        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTING_KEY1);
//
//    }
//    @Bean
//    public Binding topicBingding2(){
//        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTING_KEY2);
//
//    }
//
//    /**
//     * Fanout Exchange
//     */
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange(FANOUT_EXCHANGE);
//    }
//    @Bean
//    public Binding fanoutBingding1(){
//        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
//
//    }
//    @Bean
//    public Binding fanoutBingding2(){
//        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
//
//    }
//
//    /**
//     * Headers Exchange
//     */
//    @Bean
//    public Queue headersQueue(){
//        return new Queue(HEADERS_QUEUE);
//    }
//    @Bean
//    public HeadersExchange headersExchange(){
//        return new HeadersExchange(HEADERS_EXCHANGE);
//    }
//    @Bean
//    public Binding headersBinding(){
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("header1","value1");
//        map.put("header2","value2");
//        return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
//
//    }
}
