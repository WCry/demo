package com.zxp.api.order;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



public interface OrderAPI {

    @GetMapping("/create")
    Boolean create(@RequestParam("userId") String userId, @RequestParam("commodityCode") String commodityCode,
                @RequestParam("count") Integer count);

}
