package com.example.springbootes.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class AsyController {

    @Autowired
    private AsyTaskService asyTaskService;

    @PostMapping("/start")
    public String asyStringDemo() {
        return asyTaskService.startAsyDoing();
    }

    @PostMapping("/result")
    public String asyResultDemo(@RequestParam String id) {
        return asyTaskService.queryResult(id);
    }
}
