package com.zxp.demo.api;

import org.springframework.web.bind.annotation.GetMapping;


public interface StorageServiceAPI {

    @GetMapping(path = "/deduct")
     Boolean deduct(String commodityCode, Integer count);
}
