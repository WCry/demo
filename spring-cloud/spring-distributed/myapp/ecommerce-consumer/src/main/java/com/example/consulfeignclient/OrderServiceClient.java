package com.example.consulfeignclient;

import com.example.consulfeignapi.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangxuepei
 * */
@FeignClient(name = "consul-feign-client",path = "/consul-feign-client")
interface OrderServiceClient extends OrderService {
}
