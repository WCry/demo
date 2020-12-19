package com.zxp.demo.api;

import org.springframework.web.bind.annotation.GetMapping;

public interface OrderServiceAPI {
    @GetMapping("/create")
     Boolean create(String userId, String commodityCode, Integer count);
}
