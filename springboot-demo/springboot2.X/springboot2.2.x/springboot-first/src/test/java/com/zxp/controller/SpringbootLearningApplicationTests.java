package com.zxp.controller;

import com.zxp.scope.SingleAService;
import com.zxp.asytask.WorkAsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
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
        List<CompletableFuture> cfList = Stream.of("h1", "h2", "h3", "h4").map(v -> workAsyncService.doWork(v)).collect(Collectors.toList());
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
}
