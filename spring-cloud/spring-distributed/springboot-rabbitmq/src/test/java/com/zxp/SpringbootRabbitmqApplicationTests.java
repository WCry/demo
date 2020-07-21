package com.zxp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() throws InterruptedException {
        System.out.println(12323);
    }
    @Test
    public void sendBDirectAndListener() {
        String routeKey = "b";
        String message = "路由key：" + routeKey;

        //发送和接收 同步被消费才会执行返回
       // rabbitTemplate.sendAndReceive()
        rabbitTemplate.convertAndSend("direct.exchange", routeKey, getMessage(message));
    }
    @Test
    public void sendBDirect() {
        String routeKey = "b";
        String message = "路由key：" + routeKey;
        rabbitTemplate.convertAndSend("direct.exchange", routeKey, getMessage(message));
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
		Map<String,Object> headers =  new HashMap<>();
		headers.put("inform_type", "email");//匹配email通知消费者绑定的header
		rabbitTemplate.convertAndSend("topic.exchange", "a", getMessage(message),
				(MessagePostProcessor) headers);
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
