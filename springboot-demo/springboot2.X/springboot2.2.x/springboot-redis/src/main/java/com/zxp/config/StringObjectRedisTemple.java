package com.zxp.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public class StringObjectRedisTemple<V> extends RedisTemplate<String, V> {

    public void init() {
        setKeySerializer(RedisSerializer.string());
        // 自定义value序列化方式，序列化成json格式
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //jackson底层的序列化和反序列化使用的是ObjectMapper,我们可以通过ObjectMapper设置序列化信息
        // 设置值的默认类型，即类信息
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // SimpleModule用于设置自定义序列化器
        SimpleModule simpleModule = new SimpleModule();
        objectMapper.registerModule(simpleModule);
        // 添加进json序列化器中
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        setValueSerializer(jackson2JsonRedisSerializer);
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(RedisSerializer.string());
    }

    public StringObjectRedisTemple(RedisConnectionFactory connectionFactory) {
        init();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
