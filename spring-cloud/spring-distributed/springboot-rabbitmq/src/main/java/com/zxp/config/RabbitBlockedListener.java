package com.zxp.config;

import com.rabbitmq.client.BlockedListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Service
public class RabbitBlockedListener implements BlockedListener {
    @Override
    public void handleBlocked(String s) throws IOException {
        System.out.println("=========================connection blocked, reason: {}");
    }

    @Override
    public void handleUnblocked() throws IOException {
        System.out.println("==============================connection unblocked");
    }
}
