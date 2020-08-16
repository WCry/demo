package com.zxp.servicehi.controller;

import com.zxp.api.HelloAPI;
import com.zxp.dto.user;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloImpl implements HelloAPI {
    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }
    @RequestMapping(value = "/hi123",method = RequestMethod.GET)
    public String hi(@RequestBody user name) {
        return "hi " + name.getName() + " ,i am from port:" + port;
    }
}
