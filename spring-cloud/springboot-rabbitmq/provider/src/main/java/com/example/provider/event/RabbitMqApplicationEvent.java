package com.example.provider.event;

import org.springframework.amqp.rabbit.connection.ConnectionBlockedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author zhangxuepei
 * @since 3.0
 * 事件监听采用
 * ApplicationListener
 * 或者  @EventListener
 * 当前项目采用  @EventListener
 */
public class RabbitMqApplicationEvent implements ApplicationListener<ConnectionBlockedEvent> {

    @Override
    public void onApplicationEvent(ConnectionBlockedEvent connectionBlockedEvent) {

    }
}
