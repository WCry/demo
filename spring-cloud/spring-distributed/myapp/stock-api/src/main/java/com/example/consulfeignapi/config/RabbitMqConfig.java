package com.example.consulfeignapi.config;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class RabbitMqConfig {
    public static final String QUEUE_INFORM_STOCK = "stock.queue";
    public static final String QUEUE_INFORM_STOCK2 = "stock.queue2";
    public static final String EXCHANGE_TOPICS_STOCK="stock.exchange";
    public static final String EXCHANGE_TOPICS_STOCK2="stock.exchange2";
    public static final String ROUTINGKEY_STOCK="stock";
//    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
//    public static final String ROUTINGKEY_SMS="inform.#.sms.#";
}
