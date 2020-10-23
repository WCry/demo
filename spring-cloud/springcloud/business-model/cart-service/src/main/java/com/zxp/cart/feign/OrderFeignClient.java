package com.zxp.cart.feign;


import com.zxp.order.OrderAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "order-service")
public interface OrderFeignClient extends OrderAPI {
}
