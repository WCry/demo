package com.zxp;

import com.zxp.controller.RedisBloomFilterController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ApiApplication.class)
public class TestRedisBloomFilter {

    @Autowired
    private RedisBloomFilterController redisBloomFilterController;
    @Test
    public void testStringWithJson() {
        redisBloomFilterController.redisBloomFilter();
    }
}
