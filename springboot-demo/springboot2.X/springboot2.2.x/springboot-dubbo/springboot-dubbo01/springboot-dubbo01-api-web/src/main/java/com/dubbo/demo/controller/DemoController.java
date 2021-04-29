package com.dubbo.demo.controller;


import com.dubbo.demo.entity.po.Message;
import com.dubbo.demo.entity.vo.ApiResponse;
import com.dubbo.demo.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Reference
    private DemoService demoService;

    @RequestMapping(value = "/query")
    public ApiResponse demo() {
        try {
            List<Message> messageList = demoService.findMessage();
            return new ApiResponse(200, "操作成功", messageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(500, "系统异常");
        }
    }

}
