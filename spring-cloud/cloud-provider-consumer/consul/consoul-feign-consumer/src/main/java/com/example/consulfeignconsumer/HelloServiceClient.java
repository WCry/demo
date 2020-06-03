package com.example.consulfeignconsumer;

import com.example.consulfeignapi.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@FeignClient("${clientname}")
interface HelloServiceClient extends HelloService {}
