package com.example.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.sql.DataSourceDefinition;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Slf4j
@Component
public class MyReceiverListenerErrorHandler implements RabbitListenerErrorHandler {
    private static ConcurrentSkipListMap<Object, AtomicInteger> map = new ConcurrentSkipListMap();
    @Autowired
    private ObjectMapper objectMapper;

    /**
     *
     */
    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {
        log.error("消息接收发生了错误，消息内容:{},错误原因:{}", objectMapper.writeValueAsString(message.getPayload()), objectMapper.writeValueAsString(exception.getCause().getMessage()));
        throw new AmqpRejectAndDontRequeueException("超出次数");
    }
}
