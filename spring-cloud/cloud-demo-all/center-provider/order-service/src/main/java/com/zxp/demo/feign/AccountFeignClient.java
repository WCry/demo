package com.zxp.demo.feign;

import com.zxp.demo.api.AccountServiceAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


@FeignClient(name = "account-service")
public interface AccountFeignClient extends AccountServiceAPI {
}
