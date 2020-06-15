package com.example.order;

import com.example.consulfeignapi.StockService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangxuepei
 * */
@FeignClient(name = "consul-feign-client",path = "/consul-feign-client")
interface StockServiceClient extends StockService {
}
