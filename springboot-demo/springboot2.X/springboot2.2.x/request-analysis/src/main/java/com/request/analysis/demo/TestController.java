package com.request.analysis.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String TestGetString(){
        System.out.println("终于过来了！");
        return "你成功了，过五关斩六将";
    }
}
