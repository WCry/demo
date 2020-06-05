package com.example.consulfeignconsumer;

import com.example.consulfeignapi.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangxuepei
 *
 * name 设置调用程序的注册Consul上ID
 * Path 设置调用端的Controller接口前的路径，
 * */
@FeignClient(name = "consul-feign-client",path = "/consul-feign-client")
interface HelloServiceClient extends HelloService{
}
