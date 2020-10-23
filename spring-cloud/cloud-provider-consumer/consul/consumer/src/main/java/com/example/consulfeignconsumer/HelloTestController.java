package com.example.consulfeignconsumer;

import com.ecwid.consul.v1.ConsulClient;
import com.example.consulfeignapi.MyFeignClientsConfiguration;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RandomRule;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.ServiceInstanceChooser;
import org.springframework.cloud.consul.discovery.configclient.ConsulConfigServerAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignContext;
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
    public static ThreadLocal<String> dsa=new ThreadLocal<>();

    final ConsumerServiceClient consumerServiceClient;
//    @Autowired
//    ILoadBalancer iLoadBalancer;
    @Autowired
    DiscoveryClient discoveryClient;
//    @Autowired
//    ServiceInstance serviceInstance;
//    @Autowired
//    ConsulClient consulClient;
    @Autowired
    ServiceInstanceChooser serviceInstanceChooser;
    public HelloTestController(ConsumerServiceClient consumerServiceClient) {
        this.consumerServiceClient = consumerServiceClient;
    }

    @GetMapping("/testdd")
    public Optional<Integer> test() {
        dsa.set("dsa");
         consumerServiceClient.test();
//        Random random=new Random(10);
//        Integer calculateNumber=(random.nextInt(10)+1)*100;
//        try {
//            Thread.sleep(calculateNumber);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return Optional.ofNullable(100);
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
