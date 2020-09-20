package com.zxp.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    /**
     * 处理登录请求
     * @return
     */
    @RequestMapping("/authentication/require")
    public String require() {
        System.out.println("dasd");
        //返回登录页面
        return "login";
    }
}
