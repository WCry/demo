package com.zxp.search.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


public interface SkuFeignAPI {
    @GetMapping
     Map search(@RequestParam(required = false) Map searchMap);
}
