package com.zxp.controller;

import com.zxp.SpringbootLearningApplication;
import com.zxp.asytask.WorkAsyncService;
import com.zxp.scope.SingleAService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes= SpringbootLearningApplication.class)
public class SpringbootLearningApplicationTests {

    @Autowired
    private SingleAService classA;
    @Autowired
    private WorkAsyncService workAsyncService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void simpleTest() {
        for (int i = 0; i < 3; i++) {
            classA.printClass();
        }
    }

    @Test
    public void asyncTest() throws Exception {
        List<CompletableFuture> cfList = Stream.of("h1", "h2", "h3", "h4").
                map(v -> workAsyncService.doWork(v)).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        CompletableFuture rs = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[cfList.size()])).whenComplete((v, t) -> {
            cfList.forEach(cf -> {
                sb.append(cf.getNow(null)).append(",");
            });
        });
        try {
            rs.get(6, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------------------" + sb.toString());
    }


    @Test
    public void asyncTestCancel() throws Exception {
        Future<Boolean> completableFuture= workAsyncService.doCanCancelWork(100000033L);
        System.out.println("asa");
        Thread.sleep(1000);
        System.out.println("即将取消");
        //cancel 调用异步任务线程会立马中断 执行
        completableFuture.cancel(true);
        if(!completableFuture.isCancelled()){
            System.out.println(completableFuture.get());
            //异步任务的保存应该在主线程中进行保存 写入状态
            //不应该在异步任务线程中进行保存任务
            completableFuture.get();
        }
        Thread.sleep(10000);
    }

}
