package com.zxp.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class Confirm {
    //确认消息是否被mq接收
    public  static RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        String msgId = correlationData.getId();
        if(ack){
            System.out.println("消息成功发送到broker");
            System.out.println("消息标识是:" + msgId);
        }else {
            System.out.println("消息被拒收的原因是:" + cause);
        }
    };
    //确认消息是否被mq接收
    public  static RabbitTemplate.ConfirmCallback confirmCallback2 = (correlationData, ack, cause) -> {
        String msgId = correlationData.getId();
        if(ack){
            System.out.println("2222222222222222222222222");
            System.out.println("消息标识是:" + msgId);
        }else {
            System.out.println("消息被拒收的原因是:" + cause);
        }
    };

    //消息被mq退回
    public  static RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, desc, exchangeName, routingKey) -> {
        System.out.println("消息被退回");
        System.out.println("被退回的消息是 :" + new String(message.getBody()));
        System.out.println("被退回的消息编码是 :" + replyCode);
    };
}
