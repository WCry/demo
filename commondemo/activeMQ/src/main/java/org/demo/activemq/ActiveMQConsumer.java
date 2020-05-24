package org.demo.activemq;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
public class ActiveMQConsumer {
    public static void main(String[] args) throws Exception {
        ActiveMQConsumer activeMQConsumer=new ActiveMQConsumer();
        activeMQConsumer.testConsumerMQ();
    }


    // 直接消费
    public void testConsumerMQ() throws Exception {
        // 连接工厂
        // 使用默认用户名、密码、路径
        // 路径 tcp://host:61616
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.19.154.149:61616");
        // 获取一个连接
        Connection connection = connectionFactory.createConnection();
        // 开启连接
        connection.start();
        // 建立会话
        // 第一个参数，是否使用事务，如果设置true，操作消息队列后，必须使用 session.commit();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建队列或者话题对象
        Queue queue = session.createQueue("HelloWorld");
        // 创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        //定时接收
        while (true) {
            TextMessage message = (TextMessage) messageConsumer.receive(10);
            if (message != null) {
                System.out.println(message.getText());
            }
        }
    }
}
