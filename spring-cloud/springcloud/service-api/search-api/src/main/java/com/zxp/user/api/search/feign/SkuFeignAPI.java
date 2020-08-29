package com.zxp.user.api.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


public interface SkuFeignAPI {
    @GetMapping
     Map search(@RequestParam(required = false) Map searchMap);
}
