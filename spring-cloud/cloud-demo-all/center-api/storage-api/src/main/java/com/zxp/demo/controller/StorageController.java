package com.zxp.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：
 *
 * @author fangliangsheng
 * @date 2019/3/28
 */
@RestController
public interface StorageController {



    @GetMapping(path = "/deduct")
     Boolean deduct(String commodityCode, Integer count);
}
