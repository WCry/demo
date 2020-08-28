package com.zxp.controller;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Random;

/**
 * @author zhangxuepei
 * @since 3.0
 * Springboot
 * 可以在Redis中安装查询 使用 直接原生命令操作实现
 */
@RestController
public class RedisBloomFilter {
    private final StringRedisTemplate redisTemplate;
    @Autowired
    public RedisBloomFilter(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    static int sizeOfNumberSet =100000000;

    static Random generator = new Random();

    public static void main(String[] args) {
        test();
    }

    @RequestMapping(value = "/test/bloomFilter")
    public static String test() {
        int error = 0;
        HashSet<Integer> hashSet = new HashSet<>();
        //Funnel 漏斗  泛型对象中那些属性需要用来做Bloom运算
        // 参考类上范例说明
        //参数：总共放多少数据
        //参数：数据错误率
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), sizeOfNumberSet);
        for(int i = 0; i < sizeOfNumberSet; i++) {
            int number = generator.nextInt();
            if(filter.mightContain(number) != hashSet.contains(number)) {
                error++;
            }
            filter.put(number);
            hashSet.add(number);
        }
        System.out.println("Error count: " + error + ", " +
                "error rate = " + String.format("%f", (float)error/(float)sizeOfNumberSet));
        return null;
    }
}
