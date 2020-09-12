//package com.example.consulfeignconsumer;
//
//import com.example.consulfeignapi.HelloService;
//import org.springframework.cloud.openfeign.FeignClient;
//
///**
// * @author zhangxuepei
// * 如果使用LoadBalancer
// * 关闭时候出现异常 官网人为是正确的
// *
// *
// * feign客户端原理
// *https://www.jianshu.com/p/8c7b92b4396c
// * name 设置调用程序的注册Consul上ID
// * Path 设置调用端的Controller接口前的路径，
// * */
//@FeignClient(name = "consul-feign-client",path = "/consul-feign-client")
//public interface HelloServiceClient extends HelloService{
//}
