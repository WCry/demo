package com.example.consulfeignconsumer;

import com.example.consulfeignapi.HelloService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@FeignClient("${clientname}")
@Component
interface HelloServiceClient extends HelloService {}
