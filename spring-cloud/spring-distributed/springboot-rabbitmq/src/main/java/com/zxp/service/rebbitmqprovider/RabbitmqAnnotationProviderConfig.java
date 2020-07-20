package com.zxp.service.rebbitmqprovider;


import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public class RabbitmqAnnotationProviderConfig {
    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(value = "topic.exchange", type = "topic"), value = @Queue(value = "topic.queue.a", durable = "true"), key = "key.a.#"))
    public void RabbitMqAConfig() {
    }

    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(value = "topic.exchange", type = "topic"), value = @Queue(value = "topic.queue.b", durable = "true"), key = "key.b.#"))
    public void RabbitMqBConfig() {
    }

    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(value = "header.exchange",
            type = ExchangeTypes.HEADERS), value = @Queue(value = "header.queue", durable = "true"),
            arguments = {@Argument(name = "one", value = "a"), @Argument(name = "two", value = "b")}))
    public void RabbitMqHeaderConfig() {
    }
}
