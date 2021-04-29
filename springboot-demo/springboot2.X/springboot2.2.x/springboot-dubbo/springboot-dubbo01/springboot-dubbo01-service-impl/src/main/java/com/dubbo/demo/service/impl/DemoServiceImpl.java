package com.dubbo.demo.service.impl;

import com.dubbo.demo.entity.po.Message;
import com.dubbo.demo.mapper.DemoMapper;
import com.dubbo.demo.service.DemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 6000)
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<Message> findMessage() {
        return demoMapper.findMessage();
    }

}
