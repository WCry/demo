package com.example.consulfeignconsumer;

import com.example.consulfeignapi.HelloService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author zhangxuepei
 * 如果使用LoadBalancer
 * 关闭时候出现异常 官网人为是正确的
 *
 *
 * feign客户端原理
 *https://www.jianshu.com/p/8c7b92b4396c
 * name 设置调用程序的注册Consul上ID
 * Path 设置调用端的Controller接口前的路径，
 * Feign可以消费自身服务
 * */
@FeignClient(name = "consumer",path = "consumer",configuration = FeignInterceptorConfig.class)
public interface ConsumerServiceClient{
    @PostMapping("/test")
    Optional<Integer> test();
}
