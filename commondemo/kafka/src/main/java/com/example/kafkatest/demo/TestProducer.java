package com.example.kafkatest.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: zhangxuepei
 * Date: 2020/3/19 19:56
 * Content:
 */
@RestController
public class TestProducer {
    @Autowired
    private UserLogProducer userLogProducer;
    @Autowired
    KafkaAdmin kafkaAdmin;
    @GetMapping("/kafka")
    public void testKafka() throws JsonProcessingException {
        for (int i = 0; i < 10; i++) {
            userLogProducer.sendLog(String.valueOf(i));
        }
    }
    @Bean
    public NewTopic teststs(){
        return new NewTopic("teststs",2,(short)1);
    }
    @Bean
    public NewTopic teststA(){
        return new NewTopic("teststA",5,(short)1);
    }

    public void createTopics(){
        //kafka创建客户topic的方式
        //AdminClient adminClient= AdminClient.create(kafkaAdmin.getConfig());
        //adminClient.createTopics();
        /*通过注入NewTopic的Bean方式进行创建Topic
        * 具体参考kafkaAdmin的initialize()方法中 初始化topic
        * this.applicationContext.getBeansOfType(NewTopic.class, false, false).values();
        * 注解方式可行
        * kafkaadmin 的使用
        * https://www.jb51.net/article/181270.htm
        * */
    }
}
