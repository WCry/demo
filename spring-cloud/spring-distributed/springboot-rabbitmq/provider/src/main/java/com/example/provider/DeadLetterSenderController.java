package com.example.provider;

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
public class DeadLetterSenderController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/test/provider")
    public void send(@RequestParam("number") int number) {
        log.warn("DeadLetterSender : {}", number);
        // 这里的Exchange可以是业务的Exchange，为了方便测试这里直接往死信Exchange里投递消息
        rabbitTemplate.convertAndSend(RabbitPropertiesConstant.DEAD_LETTER_EXCHANGE, RabbitPropertiesConstant.DEAD_LETTER_TEST_ROUTING_KEY, number);
    }
}
