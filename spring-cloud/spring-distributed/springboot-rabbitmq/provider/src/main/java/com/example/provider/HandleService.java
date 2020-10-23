package com.example.provider;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class HandleService implements ChannelAwareMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(HandleService.class);

    /**
     * @param message 处理成功，这种时候用basicAck确认消息
     * ； 2、可重试的处理失败，这时候用basicNack将消息重新入列；
     * 3、不可重试的处理失败，这时候使用basicNack将消息丢弃。
     * <p>
     * basicNack(long deliveryTag, boolean multiple, boolean requeue)
     * deliveryTag:该消息的index
     * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
     * requeue：被拒绝的是否重新入队列
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        byte[] body = message.getBody();
        System.out.println(body);
    }
}