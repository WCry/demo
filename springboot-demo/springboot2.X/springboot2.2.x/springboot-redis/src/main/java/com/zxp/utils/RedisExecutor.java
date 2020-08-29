package com.zxp.utils;


import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @Auther: syh
 * @Date: 2020/7/10
 * @Description:
 */
public class RedisExecutor<T> {

    private RedisTemplate redisTemplate;

    public RedisExecutor(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 采用Redis的管道执行 一次执行多个命令
     * @param executor
     * @param <T>
     * @return
     */
    public <T> T execute(PipelineExecutor executor) {
        return (T) redisTemplate.execute((RedisCallback) conn -> {
            conn.openPipeline();
            T rst = (T) executor.exec(conn);
            conn.close();
            return rst;
        });
    }


    public<T> List<T> executePipelined(PipelineExecutor executor) {
        List<T> list = (List<T>)redisTemplate.executePipelined((RedisCallback) conn -> {
            conn.openPipeline();
            executor.exec(conn);
            conn.close();
            return null;
        });
        return list;
    }

    @FunctionalInterface
    public interface PipelineExecutor<T> {
        T exec(RedisConnection conn);
    }
}
