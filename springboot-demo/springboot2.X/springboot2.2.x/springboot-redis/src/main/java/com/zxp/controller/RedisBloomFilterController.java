package com.zxp.controller;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.zxp.utils.RedisBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Random;

/**
 * @author zhangxuepei
 * @since 3.0
 * Springboot
 * 可以在Redis中安装查询 使用 直接原生命令操作实现
 */
@RestController
public class RedisBloomFilterController {
    private final StringRedisTemplate redisTemplate;
    @Autowired
    public RedisBloomFilterController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    static int sizeOfNumberSet =10000;

    static Random generator = new Random();


    @RequestMapping(value = "/test/bloomFilter")
    public  String testBloomFilter() {
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

    @RequestMapping(value = "/test/redis/bloomFilter")
    public  String redisBloomFilter() {
        int error = 0;
        HashSet<String> hashSet = new HashSet<>();

        RedisBloomFilter<String> filter = RedisBloomFilter.create(redisTemplate,Funnels.stringFunnel(Charset.forName("UTF-8")), sizeOfNumberSet,0.03D);
        for(int i = 0; i < sizeOfNumberSet; i++) {
            int number = generator.nextInt();
            if(filter.mightContain(String.valueOf(number)) != hashSet.contains(number)) {
                error++;
            }
            filter.put(String.valueOf(number));
            hashSet.add(String.valueOf(number));
        }
        System.out.println("Error count: " + error + ", " +
                "error rate = " + String.format("%f", (float)error/(float)sizeOfNumberSet));
        return null;
    }

}
