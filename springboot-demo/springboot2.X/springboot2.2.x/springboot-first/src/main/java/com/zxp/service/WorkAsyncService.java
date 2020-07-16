package com.zxp.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author zhangxuepei
 * @since 3.0
 * spring  Boot 结合CompletableFuture 实现异步调用
 */
@Service
public class WorkAsyncService {
    @Async
    public CompletableFuture<String> doWork(String value){
        System.out.println("--------start work------" + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("-------end work----------");
        return CompletableFuture.completedFuture(value);
    }
}
