package com.example.consulfeignconsumer;

import com.example.consulfeignapi.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangxuepei
 */
@FeignClient(name = "consul-feign-client",path = "/consul-feign-client")
interface HelloServiceClient extends HelloService{
}
