package com.zxp.cart.feign;

import com.zxp.user.api.order.OrderAPI;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "order-service", url = "localhost:8082")
public interface OrderFeignClient extends OrderAPI {
}
