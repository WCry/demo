package com.zxp.servicehi.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class RedisPoolFactory {

    final RedisConfig  redisConfig;

    public RedisPoolFactory(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    /**
     * 将redis连接池注入spring容器
     * @return
     */
    @Bean
    public JedisPool JedisPoolFactory(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisConfig.getPoolMaxIdle());
        config.setMaxTotal(redisConfig.getPoolMaxTotal());
        config.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout()*1000, redisConfig.getPassword(), 0);
        return jp;
    }

}
