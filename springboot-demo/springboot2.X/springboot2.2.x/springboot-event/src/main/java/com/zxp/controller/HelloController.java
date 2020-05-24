package com.zxp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class HelloController {
    private final ApplicationContext applicationEventPublisher;
    private final SimpleApplicationEventMulticaster dsa;
    //注解方式获取配置文件中的属性
    @Value("${server.port}")
    private String port;

    @Autowired
    public HelloController(ApplicationContext applicationEventPublisher, SimpleApplicationEventMulticaster dsa) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.dsa = dsa;
    }

    @GetMapping("/test/{name}")
    public String hello(@PathVariable(name = "name") String name) {
        TestTaskEvent taskEvent = new TestTaskEvent(this);
        taskEvent.setTaskID(UUID.randomUUID().toString());
        //发布事件
        applicationEventPublisher.publishEvent(taskEvent);
        return "hello , " + port + ":" + name;
    }

    /**
     * 监听事件, 事件是同步执行的
     *
     * @param event
     */
    @EventListener
    @Async
    public void testTaskEventListener(TestTaskEvent event) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(event.getTaskID());
    }

}
