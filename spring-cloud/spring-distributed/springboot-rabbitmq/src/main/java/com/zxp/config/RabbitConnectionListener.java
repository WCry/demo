package com.zxp.config;

import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Service
public class RabbitConnectionListener implements ConnectionListener {
    @Autowired
    private RabbitBlockedListener rabbitBlockedListener;
    @Override
    public void onCreate(Connection connection) {
        System.out.println("================onCreate: {}");
        connection.addBlockedListener(rabbitBlockedListener);
    }

    @Override
    public void onClose(Connection connection) {
        System.out.println("================onClose: {}");
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        System.out.println("================onShutDown: {}");
    }
}
