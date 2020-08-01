package com.example.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@SpringBootTest(classes = ProviderApplication.class)
public class TestDeadLetterProvider {

    @Autowired
    private DeadLetterSenderController deadLetterSenderController;

    @Test
    public void testSendDeadLetterQueue() {
        deadLetterSenderController.send(15);
    }
}
