package com.example.springstream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@EnableBinding(ProducerChannelClient.class)  //启动绑定
@Service
public class RabbitMQSender {

    @Autowired
    private ProducerChannelClient barista;

    public String sendMessage(Object message, Map<String, Object> properties) {
        try {
            MessageHeaders messageHeaders = new MessageHeaders(properties);
            Message msg = MessageBuilder.createMessage(message, messageHeaders);
            boolean sendResult = barista.logoutput().send(msg);
            System.out.println("sendResult"+sendResult);
        } catch (Exception e) {
            throw new RuntimeException("生产者发送数据失败");
        }
        return null;
    }

}
