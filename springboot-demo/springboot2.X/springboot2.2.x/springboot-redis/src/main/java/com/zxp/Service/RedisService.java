package com.zxp.Service;

import java.util.Set;

public interface RedisService {

    /**
     * 获取可以省下的生存时间,对应redis命令：TTL key
     *
     * @param key
     *
     * @return
     */
    Long getExpire(String key);

    /**
     * 设置key超时时间,对应redis命令：EXPIRE timeout(单位秒)
     *
     * @param key
     * @param timeout
     */
    void expire(String key, Long timeout);

    /**
     * 给key增加1,对应redis命令：INCR key
     *
     * @param key
     * @param time
     *
     * @return
     */
    Long increment(String key, long time);

    /**
     * 查找符合给定模式pattern的key，对应redis命令：KEYS pattern
     *
     * @param pattern
     *
     * @return
     */
    Set<String> keys(String pattern);

    /**
     * 删除key，对应redis命令：DEL key
     *
     * @param key
     */
    void delete(String key);

    /**
     * 将数据存储在redis中，对应redis命令：SET key value
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 将数据存储在redis中，并设置超时时间，对应redis命令：SET key value EX timeout
     *
     * @param key
     * @param value
     */
    void set(String key, String value, Long timeout);

    /**
     * 根据key获取value，对应redis命令：GET key
     *
     * @param key
     *
     * @return
     */
    String get(String key);

}
