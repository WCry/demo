package com.example.order.config.mq;


import com.example.consulfeignapi.config.RabbitMqConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * user:zxp
 * Day:2020,06,14
 **/
@Configuration
public class RabbitmqConfig {
    //发送 设置Converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public Queue StockQueue() {
        return new Queue(RabbitMqConfig.QUEUE_INFORM_STOCK, true);
    }

    @Bean
    public DirectExchange StockExchange() {
        return new DirectExchange(RabbitMqConfig.EXCHANGE_TOPICS_STOCK, true, true);
    }
    @Bean
    Binding bindingStockExchange2Queue(@Qualifier("StockQueue")Queue queue,@Qualifier("StockExchange")DirectExchange stockExchange ) {
        return BindingBuilder.bind(queue).to(stockExchange).with(RabbitMqConfig.ROUTINGKEY_STOCK);
    }
}
