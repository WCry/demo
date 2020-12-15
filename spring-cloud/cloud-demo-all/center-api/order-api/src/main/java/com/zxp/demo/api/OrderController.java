package com.zxp.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
/**
 * Description：
 *
 * @author fangliangsheng
 * @date 2019-04-04
 */

public interface OrderController {
    @GetMapping("/create")
     Boolean create(String userId, String commodityCode, Integer count);
}
