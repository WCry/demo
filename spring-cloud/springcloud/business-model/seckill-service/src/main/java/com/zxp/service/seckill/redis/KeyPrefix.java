package com.zxp.service.seckill.redis;

/**
 * 缓冲key前缀
 */
public interface KeyPrefix {

    /**
     * 有效期
     * @return
     */
    public int expireSeconds();

    /**
     * 前缀
     * @return
     */
    public String getPrefix();
}
