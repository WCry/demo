package com.dubbo.demo.controller;

import com.dubbo.demo.ApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApiApplication.class)
class DemoControllerTest {
    @Autowired
    DemoController demoController;

    @Test
    void demo() {
        demoController.demoService();
    }
    @Test
    void OtherService() {
        demoController.demoService();
        System.out.println(demoController.otherService());
    }
}