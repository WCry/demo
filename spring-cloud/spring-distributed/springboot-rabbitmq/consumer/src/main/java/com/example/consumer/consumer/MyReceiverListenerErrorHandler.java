package com.example.consumer.consumer;

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
 * 可以通过消息异常处理，或者死信，
 * 自定义消息处理进行异常消息的记录
 * 如果需要通知其他应用处理，可以采用死信
 * 如果自身应用处理可以使用异常处理
 */
@Slf4j
@Component
public class MyReceiverListenerErrorHandler implements RabbitListenerErrorHandler {
    private static ConcurrentSkipListMap<Object, AtomicInteger> map = new ConcurrentSkipListMap();
    @Autowired
    private ObjectMapper objectMapper;

    /**
     *MessagingMessageListenerAdapter 的if (this.errorHandler != null) { 中进行处理的
     */
    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {
        log.error("消息接收发生了错误，消息内容:{},错误原因:{}",
                objectMapper.writeValueAsString(message.getPayload()),
                objectMapper.writeValueAsString(exception.getCause().getMessage()));
        //抛出异常 不在放回队里当中
        throw new AmqpRejectAndDontRequeueException("超出次数");
    }
}
