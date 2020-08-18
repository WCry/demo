package com.zxp.sericefeign;

import com.zxp.api.HelloAPI;
import com.zxp.dto.user;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-hi",path = "/hello")
public interface HelloClient extends HelloAPI {
}

