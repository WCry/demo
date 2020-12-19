package com.zxp.demo.api;

import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

/**
 * Description：
 *
 * @author fangliangsheng
 */

public interface AccountServiceAPI {


    @RequestMapping("/debit")
    Boolean debit(String userId, BigDecimal money);
}
