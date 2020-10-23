package com.example.provider;


import com.rabbitmq.client.BuiltinExchangeType;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Configuration
public class RabbitConfig {
    @Autowired
    CachingConnectionFactory connectionFactory;

    //监听处理类
    @Bean
    @Scope("prototype")
    public HandleService handleService() {
        return new HandleService();
    }

    //动态创建queue，命名为：hostName.queue1【192.168.1.1.queue1】,并返回数组queue名称
    @Bean
    public String[] mqMsgQueues() throws AmqpException, IOException {
        String[] queueNames = new String[1];
        String queueName = "aaa";
        String exchangeName = "bbb";
        connectionFactory.createConnection().createChannel(false).
                queueDeclare(queueName, true, false, false,
                        null);
        connectionFactory.createConnection().createChannel(false).
                exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
        connectionFactory.createConnection().createChannel(false).
                queueBind(queueName, exchangeName, queueName);
        queueNames[0] = queueName;
        return queueNames;
    }

    //创建监听器，监听队列
    @Bean
    public SimpleMessageListenerContainer mqMessageContainer(HandleService handleService) throws AmqpException, IOException {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(mqMsgQueues());
        container.setExposeListenerChannel(true);
        container.setPrefetchCount(10);//设置每个消费者获取的最大的消息数量
        container.setConcurrentConsumers(5);//消费者个数
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(handleService);//监听处理类
        return container;
    }
}
