package com.example.provider.controller;

import com.example.provider.constant.RabbitPropertiesConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
@Slf4j
public class TTLLetterSenderController {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TTLLetterSenderController(RabbitTemplate rabbitTemplate) {this.rabbitTemplate = rabbitTemplate;}

    @GetMapping("/test/ttl/provider")
    public void send(@RequestParam("number") int number) {
        // 这里的Exchange可以是业务的Exchange，为了方便测试这里直接往死信Exchange里投递消息
        rabbitTemplate.convertAndSend(RabbitPropertiesConstant.TTL_LETTER_EXCHANGE, RabbitPropertiesConstant.TTL_LETTER_ROUTING_KEY, number);
    }
}
