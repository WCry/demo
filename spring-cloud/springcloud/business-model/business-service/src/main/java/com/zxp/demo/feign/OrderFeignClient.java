package com.zxp.demo.feign;

import com.zxp.api.order.OrderAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "order-service", url = "localhost:8082")
public interface OrderFeignClient extends OrderAPI {
}
