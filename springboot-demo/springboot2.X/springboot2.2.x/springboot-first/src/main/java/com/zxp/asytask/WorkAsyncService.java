package com.zxp.asytask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.spi.ior.ObjectKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author zhangxuepei
 * @since 3.0
 * spring  Boot 结合CompletableFuture 实现异步调用
 */
@Slf4j
@Service
public class WorkAsyncService {
    @Autowired
    ObjectMapper objectMapper;
    @Async
    public CompletableFuture<String> doWork(String value) {
        log.debug("--------start work------" + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("-------end work----------");
        return CompletableFuture.completedFuture(value);
    }

    @Async
    public Future<Boolean> doCanCancelWork(Long value) throws InterruptedException {
        log.debug("--------start work------" + Thread.currentThread().getName());
        for (int i = 1; i < value / 2; i++) {
            if (value / i == 0) {
                return new AsyncResult<>(Boolean.FALSE);
            }
            System.out.println("正在计算:"+i);
            Thread.sleep(1000);
        }
        log.debug("-------end work----------");
        return new AsyncResult<>(Boolean.TRUE);
    }
}
