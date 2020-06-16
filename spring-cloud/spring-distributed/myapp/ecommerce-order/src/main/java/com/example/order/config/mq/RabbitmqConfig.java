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

//    //队列 起名：TestDirectQueue
//    public Queue DirectQueue() {
//        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
//        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
//        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
//        //  return new Queue("TestDirectQueue",true,true,false);
//        //一般设置一下队列的持久化就好,其余两个就是默认false
//        return new Queue("DirectQueue", true);
//    }
//
//    //Direct交换机 起名：TestDirectExchange
//    @Bean
//    DirectExchange DirectExchange() {
//        //  return new DirectExchange("TestDirectExchange",true,true);
//        return new DirectExchange("DirectExchange", true, true);
//    }
//
//    /**
//     * 绑定队列和交换机 并设置匹配键：TestDirectRouting
//     *
//     * @return
//     */
//    @Bean
//    Binding bindingDirect() {
//        return BindingBuilder.bind(DirectQueue()).to(DirectExchange()).with("DirectRouting");
//    }
//
//
//    @Bean
//    DirectExchange lonelyDirectExchange() {
//        return new DirectExchange("lonelyDirectExchange");
//    }
}
