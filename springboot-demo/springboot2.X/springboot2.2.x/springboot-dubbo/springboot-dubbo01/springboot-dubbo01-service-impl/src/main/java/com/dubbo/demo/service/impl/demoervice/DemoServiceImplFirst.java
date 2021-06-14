package com.dubbo.demo.service.impl.demoervice;

import com.dubbo.demo.entity.po.Message;
import com.dubbo.demo.mapper.DemoMapper;
import com.dubbo.demo.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;


@DubboService(timeout = 6000,group = "implFirst")
public class DemoServiceImplFirst implements DemoService{

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<Message> findMessage() {
        System.out.println("imple122");
        Message message=new Message();
        return Collections.singletonList(message);
    }


    public String getName() {
        return "ddd";
    }
}
