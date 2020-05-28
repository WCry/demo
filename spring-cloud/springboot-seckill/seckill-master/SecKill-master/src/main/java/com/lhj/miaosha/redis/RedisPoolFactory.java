package com.lhj.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ：LHJ
 * @date ：2019/7/23 18:47
 * @description：获取JedisPool,用于生成Jedis,jedis用完后要使用jedis.close()放回连接池，
 * 否则会导致连接不够用
 */
@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;

    @Bean
    public JedisPool JedisPoolFactory() {

        JedisPoolConfig poolConfig = new JedisPoolConfig();

        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout()*1000, redisConfig.getPassword(), 0);
        /**
          *database:0;redis默认支持16个库，不指定默认用0号库
          */
        return jp;
    }

}

