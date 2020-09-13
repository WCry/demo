package com.example.consulfeignconsumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author zhangxuepei
 */
@RestController
public class HelloTestController {
    final ConsumerServiceClient consumerServiceClient;

    public HelloTestController(ConsumerServiceClient consumerServiceClient) {
        this.consumerServiceClient = consumerServiceClient;
    }

    @PostMapping("/test")
    public Optional<Integer> test() {
        Random random=new Random(10);
        Integer calculateNumber=(random.nextInt(10)+1)*100;
//        try {
//            Thread.sleep(calculateNumber);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return Optional.ofNullable(calculateNumber);
    }

    @GetMapping("/test2")
    public  List<Integer> test2(String name) throws ExecutionException, InterruptedException {
        ExecutorService threadPoolExecutor= Executors.newFixedThreadPool(5);
        List<Future<Optional<Integer>>> futures=new ArrayList<>();
        List<Integer> count=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
           Future<Optional<Integer>> dsad= threadPoolExecutor.submit(new NumberCalculate());
            futures.add(dsad);
        }
        Iterator<Future<Optional<Integer>>> iterator=  futures.iterator();
        while (iterator.hasNext()){
            count.add(iterator.next().get().get());
            iterator.remove();
        }
        return count;
    }

    class NumberCalculate implements Callable<Optional<Integer>> {
        @Override
        public Optional<Integer> call() throws Exception {
            return consumerServiceClient.test();
        }
    }

}
