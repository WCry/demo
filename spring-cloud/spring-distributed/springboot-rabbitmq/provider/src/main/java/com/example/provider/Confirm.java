package com.example.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class Confirm {
    /**
      *消息成功被接收到了，消息到达Broker之后返回
     */
    public  static RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        String msgId = correlationData.getId();
        if(ack){
            System.out.println("消息成功发送到broker");
            System.out.println("消息标识是:" + msgId);
        }else {
            System.out.println("消息被拒收的原因是:" + cause);
        }
    };

    /**
     * 启动消息失败返回，比如路由不到队列时触发回调，或者持久化失败，或者超时未处理
     *  消息被退回了
     */
    public  static RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, desc, exchangeName, routingKey) -> {
        System.out.println("消息被退回");
        System.out.println("被退回的消息是 :" + new String(message.getBody()));
        System.out.println("被退回的消息编码是 :" + replyCode);
    };
}
