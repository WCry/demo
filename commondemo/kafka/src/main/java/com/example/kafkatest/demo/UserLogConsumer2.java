package com.example.kafkatest.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Optional;

//Spring boot 一定需要注册成为组件 才能使用
@Component
public class UserLogConsumer2 {
    @KafkaListener(id="ddddd",topicPartitions ={@TopicPartition(topic = "test", partitions = { "2" })})
    public void consumer1(ConsumerRecord<?,?> consumerRecord){
        System.err.println("2222222222222222");
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMessage.isPresent()){
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            System.err.println("消费消息1:"+message);
        }
    }
}
