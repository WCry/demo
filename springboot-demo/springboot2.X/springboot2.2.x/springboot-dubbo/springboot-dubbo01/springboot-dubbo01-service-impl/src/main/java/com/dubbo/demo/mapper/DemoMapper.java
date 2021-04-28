package com.dubbo.demo.mapper;

import com.dubbo.demo.entity.po.Message;

import java.util.List;

public interface DemoMapper {

    public List<Message> findMessage();

}
