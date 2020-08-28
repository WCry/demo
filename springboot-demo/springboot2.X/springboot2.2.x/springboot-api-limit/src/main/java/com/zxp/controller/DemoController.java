package com.zxp.controller;

import com.zxp.A;
import com.zxp.annotation.AccessLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wyj
 * @date 2019-10-30 09:51
 */
@RestController
public class DemoController {
    @GetMapping(value = "/test2", produces
            = "application/json; charset=utf-8")
    public String test22(@Valid A dsad) {
        return "请求成功";
    }

    @AccessLimit(seconds = 5, maximum = 5, needLogin = true)
    @RequestMapping(value = "/test", produces = "application/json; charset=utf-8")
    public String test() {
        return "请求成功";
    }
}