package com.example.consumer.handler;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class RabbitPropertiesConstant {
    //模拟死信队列，进行重定向处理，对于需要保证消息完成消费基本都需要做
    public static final String DEAD_LETTER_EXCHANGE = "TDL_EXCHANGE";
    public static final String DEAD_LETTER_ROUTING_KEY = "TDL_KEY";
    public static final String DEAD_LETTER_REDIRECT_ROUTING_KEY = "T_KEY_R";

    public static final String DEAD_LETTER_QUEUE = "TDL_QUEUE";
    public static final String REDIRECT_QUEUE = "T_REDIRECT_QUEUE";

    //模拟延迟消费队列
    public static final String TTL_LETTER_EXCHANGE = "TTL_EXCHANGE";
    public static final String TTL_LETTER_ROUTING_KEY = "TTL_KEY";
    public static final String TTL_LETTER_REDIRECT_ROUTING_KEY = "TTL_KEY_R";
    public static final String TTL_LETTER_QUEUE = "TTL_QUEUE";
    public static final String TTL="1000";
    public static final String TTL_REDIRECT_QUEUE = "TTL_REDIRECT_QUEUE";
}
