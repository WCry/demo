package com.zxp.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：
 *
 * @author fangliangsheng
 * @date 2019/3/28
 */
@RestController
public interface StorageServiceAPI {



    @GetMapping(path = "/deduct")
     Boolean deduct(String commodityCode, Integer count);
}
