package com.example.sentinel.consumer;


import com.example.api.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "service-hi")
public interface DispatchServiceHi {
    @RequestMapping(value = "/hi123", method = RequestMethod.GET)
    String sayHiOne(@RequestBody User json);
}

