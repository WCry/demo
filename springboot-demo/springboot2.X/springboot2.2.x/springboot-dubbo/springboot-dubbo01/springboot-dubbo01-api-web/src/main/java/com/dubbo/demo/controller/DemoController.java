package com.dubbo.demo.controller;


import com.dubbo.demo.entity.po.Message;
import com.dubbo.demo.entity.vo.ApiResponse;
import com.dubbo.demo.service.DemoService;
import com.dubbo.demo.service.OtherService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @DubboReference
    private DemoService demoService;
    //明确使用的Dubbo使用配置文件中哪一个 consumer的配置
    @DubboReference(consumer = "otherService")
    private OtherService otherService;

    @RequestMapping(value = "/query")
    public ApiResponse demoService() {
        try {
            List<Message> messageList = demoService.findMessage();
            System.out.println(messageList.size());
            return new ApiResponse(messageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(500, "系统异常");
        }
    }
    @RequestMapping(value = "/query2")
    public ApiResponse otherService() {
        try {
            System.out.println(otherService.findMessage());
            return new ApiResponse("ddd");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(500, "系统异常");
        }
    }

}
