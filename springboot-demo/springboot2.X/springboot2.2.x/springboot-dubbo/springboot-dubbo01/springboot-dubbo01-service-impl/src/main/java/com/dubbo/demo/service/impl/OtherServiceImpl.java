package com.dubbo.demo.service.impl;

import com.dubbo.demo.service.OtherService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class OtherServiceImpl implements OtherService {

    @Override
    public String findMessage() {
        return "OtherService";
    }
}
