package com.dubbo.demo.service;

import com.dubbo.demo.entity.po.Message;

import java.util.List;

public interface DemoService {

     List<Message> findMessage();

}
