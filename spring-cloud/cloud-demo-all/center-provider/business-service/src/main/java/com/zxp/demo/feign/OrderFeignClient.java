package com.zxp.demo.feign;

import com.zxp.demo.api.OrderServiceAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "order-service")
public interface OrderFeignClient extends OrderServiceAPI {

}
