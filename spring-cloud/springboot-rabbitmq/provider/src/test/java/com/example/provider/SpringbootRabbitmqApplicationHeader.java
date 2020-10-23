package com.example.provider;//package com.zxp;
//
//import com.zxp.service.Sender_Headers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageDeliveryMode;
//import org.springframework.amqp.core.MessagePostProcessor;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringbootRabbitmqApplicationHeader {
//
//    @Autowired
//    private Sender_Headers sender_Headers;
//
//    /**
//     * headers模式不满足匹配规则
//     */
//    @Test
//    public void test_match_no() {
//        /**
//         * 声明消息 (消息体, 消息属性)
//         */
//        MessageProperties messageProperties = new MessageProperties();
//        // 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
//        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
//        messageProperties.setContentType("UTF-8");
//        messageProperties.setHeader("three", "c");
//
//        Message message = new Message("hello,rabbit_headers_no！".getBytes(), messageProperties);
//
//        sender_Headers.send("header.exchange", message);
//    }
//    @Test
//    /**
//     * headers模式满足全部匹配规则
//     */
//    public void test_match_all() {
//        Map<String, Object> map_all = new HashMap<>();
//        map_all.put("one", "a");
//        map_all.put("two", "b");
//
//        /**
//         * 声明消息 (消息体, 消息属性)
//         */
//        MessageProperties messageProperties = new MessageProperties();
//        // 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
//        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
//        messageProperties.setContentType("UTF-8");
//        messageProperties.getHeaders().putAll(map_all);
//
//        Message message = new Message("hello,rabbit_headers_all！".getBytes(), messageProperties);
//
//        sender_Headers.send("header.exchange", message);
//    }
//
//    /**
//     * headers模式满足部分匹配规则
//     */
//    public void test_match_any() {
//        Map<String, Object> map_any = new HashMap<>();
//        map_any.put("one", "a");
//
//        /**
//         * 声明消息 (消息体, 消息属性)
//         */
//        MessageProperties messageProperties = new MessageProperties();
//        // 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
//        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
//        messageProperties.setContentType("UTF-8");
//        messageProperties.getHeaders().putAll(map_any);
//
//        Message message = new Message("hello,rabbit_headers_any！".getBytes(), messageProperties);
//
//        sender_Headers.send("header.exchange", message);
//    }
//}
