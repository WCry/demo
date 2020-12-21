package com.wyj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wyj.entity.po.Message;
import com.wyj.entity.vo.ApiResponse;
import com.wyj.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.DriverManager;
import java.util.List;
import java.util.ServiceLoader;

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
