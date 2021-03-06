package com.zxp.controller;

import com.zxp.scope.RequestScopeAction;
import com.zxp.scope.SessionScopeAction;
import org.springframework.beans.factory.annotation.Value;
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
public class WebScopeController {
    private final RequestScopeAction requestScopeAction;
    private final SessionScopeAction sessionScopeAction;

    public WebScopeController(RequestScopeAction requestScopeAction, SessionScopeAction sessionScopeAction) {
        this.requestScopeAction = requestScopeAction;
        this.sessionScopeAction = sessionScopeAction;
    }

    @GetMapping("/addRequestValue")
    public String addRequestScope(String name) {

        if (name.equals("李四")) {
            requestScopeAction.setName(name);
        }
        System.out.println(requestScopeAction);
        System.out.println(requestScopeAction.getName());
        return "设置的Request的Value是"+requestScopeAction.getName();
    }

    @GetMapping("/testGetRequestValue")
    public String getRequestScope() {
        return "获取到的Request的Value是"+requestScopeAction.getName();
    }
    @GetMapping("/addSessionValue")
    public String addSessionScope(String name) {
        if (name.equals("李四")) {
            sessionScopeAction.setName(name);
        }
        System.out.println(sessionScopeAction);
        System.out.println(sessionScopeAction.getName());
        return "设置的Session的Value"+sessionScopeAction.getName();
    }
    @GetMapping("/getSessionValue")
    public String getSessionScope() {
        System.out.println(sessionScopeAction);
        System.out.println(sessionScopeAction.getName());
        return "获取到的Session的Value"+sessionScopeAction.getName();
    }
}
