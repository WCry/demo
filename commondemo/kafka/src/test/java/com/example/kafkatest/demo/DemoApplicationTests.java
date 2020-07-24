package com.example.kafkatest.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private UserLogProducer userLogProducer;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private KafkaAdmin kafkaAdmin;
    @Test
    void contextLoads() {

    }
    @Test
    void testConsumerGroup() throws InterruptedException {
        System.out.println(kafkaAdmin.getConfig());
        for (int i = 0; i < 2; i++) {
            try {
                userLogProducer.sendLog(String.valueOf(i));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void testKafkaTPS(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ExecutorService executorService=Executors.newFixedThreadPool(10);
        Runnable runnable= () -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    userLogProducer.sendLog(String.valueOf(i));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.shutdown();
        try {
            executorService.awaitTermination(2, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //测试产生速率单机持续写入速率是20万tps
        //测试过程中消耗生产者的机器cpu，采用批量投送的方式
        //对于Kafka安装机器的CPU和内存消耗不多。
        System.out.println("总时间："+localDateTime.until(LocalDateTime.now(), ChronoUnit.SECONDS));
    }
}
