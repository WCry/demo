package com.zxp;


import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis的String类型可以使用任意类型 可以使bit操作
 * bitmap bloomerFilter 进行是否存在过滤
 * Hylogger 用来进行大数据量的基数统计，采用的是大数量掷硬币概率
 * 采用采用 bitFiled 存储bit数组存储 数值
 */
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        DefaultSingletonBeanRegistry
        SpringApplication.run(ApiApplication.class, args);
    }
}
