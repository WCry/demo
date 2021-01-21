package com.example.springstream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class SpringStreamApplicationTests {
    @Autowired
    RabbitMQSender rabbitMQSender;
    @Test
    void contextLoads() {
        rabbitMQSender.sendMessage("dsad",new HashMap<String, Object>());
    }

}
