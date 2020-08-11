package com.zxp.controller;

import com.zxp.config.RequestScopeAction;
import com.zxp.config.SessionScopeAction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 * 测试首先代用add 端口设置Request对应的值，然后嗲用get端口获取值
 * request在两个请求中不能获取值
 * session在同一个浏览器中可以获取到设置的值
 */
@RestController
public class TestWebScope {
    private final RequestScopeAction requestScopeAction;
    private final SessionScopeAction sessionScopeAction;

    public TestWebScope(RequestScopeAction requestScopeAction, SessionScopeAction sessionScopeAction) {
        this.requestScopeAction = requestScopeAction;
        this.sessionScopeAction = sessionScopeAction;
    }

    @GetMapping("/addRequestValue")
    private String addRequestScope(String name) {
        if (name.equals("李四")) {
            requestScopeAction.setName(name);
        }
        System.out.println(requestScopeAction);
        System.out.println(requestScopeAction.getName());
        return "设置的Request的Value是"+requestScopeAction.getName();
    }

    @GetMapping("/testGetRequestValue")
    private String getRequestScope() {
        return "获取到的Request的Value是"+requestScopeAction.getName();
    }
    @GetMapping("/addSessionValue")
    private String addSessionScope(String name) {
        if (name.equals("李四")) {
            sessionScopeAction.setName(name);
        }
        System.out.println(sessionScopeAction);
        System.out.println(sessionScopeAction.getName());
        return "设置的Session的Value"+sessionScopeAction.getName();
    }
    @GetMapping("/getSessionValue")
    private String getSessionScope() {
        System.out.println(sessionScopeAction);
        System.out.println(sessionScopeAction.getName());
        return "获取到的Session的Value"+sessionScopeAction.getName();
    }
}
