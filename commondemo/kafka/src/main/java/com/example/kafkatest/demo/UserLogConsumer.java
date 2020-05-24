package com.example.kafkatest.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserLogConsumer {
    @KafkaListener(id="dd",topicPartitions = { @TopicPartition(topic = "test", partitions = { "1" })})
    public void consumer(ConsumerRecord<?,?> consumerRecord){
        System.out.println("fenqu"+consumerRecord.partition());
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMessage.isPresent()){
            //得到Optional实例中的值
            Object message = kafkaMessage.get();

            System.err.println("消费消息0:"+message);
        }
    }
}
