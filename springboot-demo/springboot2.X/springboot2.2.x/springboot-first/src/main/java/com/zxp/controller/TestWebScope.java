package com.zxp.controller;

import com.zxp.LoginAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class TestWebScope {
    @Autowired
    private LoginAction loginAction;
    @GetMapping("/testscope")
    private String testScope(String name){
        if(name.equals("李四")){
            loginAction.setName(name);
        }
        System.out.println(loginAction);
        System.out.println(loginAction.getName());
        return "调用成功！";
    }
}
