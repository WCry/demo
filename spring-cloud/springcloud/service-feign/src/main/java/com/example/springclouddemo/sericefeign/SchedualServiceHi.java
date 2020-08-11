package com.example.springclouddemo.sericefeign;

import common.user;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-hi")
public interface SchedualServiceHi {
 /*   @RequestMapping(value = "/getALLFeature",method = RequestMethod.POST)
    String sayHiFromClientOne(@RequestParam(value = "json") String json);*/
    @RequestMapping(value = "/hi123",method = RequestMethod.GET)
    String sayHiOne(@RequestBody user json);
}

