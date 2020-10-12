package com.example.provider;


import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Ignore
@SpringBootTest(classes = ProviderApplication.class)
public class SpringbootRabbitmqApplicationTests {
    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Autowired
    public AmqpAdmin amqpAdmin;
    @Test
    public void contextLoads() throws InterruptedException {
        Thread topicA = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                try {
                    send2ATopic();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread topicB = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                try {
                    send2BTopic();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        topicA.start();
        topicA.join();
        //topicB.start();
    }

    @Test
    public void sendBDirectAndListener() {
        String routeKey = "b";
        String message = "路由key：" + routeKey;
        //设置消息唯一标识，生产上可以准确定位消息,用来在方法方法和事件之间传递唯一标识
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().substring(0, 4));
        rabbitTemplate.setConfirmCallback(SendConfirm.confirmCallback);
        rabbitTemplate.setReturnCallback(SendConfirm.returnCallback);
        //针对于发布者新建一个链接，发布者和监听采用不同链接不相互影响
        rabbitTemplate.setUsePublisherConnection(true);
        //rabbitTemplate.setMandatory(true);
//        rabbitTemplate.convertAndSend("direct.exchange", routeKey, getMessage(message),correlationData);
//        rabbitTemplate.convertAndSend("direct.exchange", routeKey, getMessage(message),correlationData);

        rabbitTemplate.convertAndSend("direct.exchange", routeKey, getMessage(message),correlationData);

    }

    @Test
    public void sendBDirect() {
        String routeKey = "b";
        String message = "路由key：" + routeKey;
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().substring(0, 4));
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.convertAndSend("direct.exchange", routeKey, getMessage(message), correlationData);
    }

    @Test
    public void sendADirect() {
        String routeKey = "a";
        String message = "路由key：" + routeKey;
        rabbitTemplate.convertAndSend("direct.exchange", routeKey, getMessage(message));
    }

    @Test
    public void send2Fanout() {
        String routeKey = "key.a.aas";
        String message = "路由key：" + routeKey;
        rabbitTemplate.convertAndSend("fanout.exchange", routeKey, getMessage(message));
    }

    @Test
    public void send2ATopic() throws InterruptedException {
        String routeKey = "key.a.aas";
        String message = "路由key：" + routeKey;
        rabbitTemplate.convertAndSend("topic.exchange", routeKey, getMessage(message));
    }

    @Test
    public void send2BTopic() throws InterruptedException {
        String routeKey = "key.b.aas";
        String message = "路由key：" + routeKey;
        rabbitTemplate.convertAndSend("topic.exchange", routeKey, getMessage(message));
    }

    @Test
    public void send2Header() throws InterruptedException {
        String message = "email inform to user";
        Map<String, Object> headers = new HashMap<>();
        headers.put("inform_type", "email");//匹配email通知消费者绑定的header
        rabbitTemplate.convertAndSend("topic.exchange", "a", getMessage(message), (MessagePostProcessor) headers);
    }

    private Map<String, Object> getMessage(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", message);
        map.put("data", message);
        return map;
    }

    @Test
    public void receive() {
        //直接使用RabbitMqTemplate进行队列消息接收
        Object o = rabbitTemplate.receiveAndConvert("direct.queue");
        o.getClass();
        System.out.println(o.getClass());
        System.out.println(o);
    }

}
