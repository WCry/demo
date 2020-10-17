package com.zxp.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    /**
     * 处理认证页面请求
     * @return 返回登录页面
     */
    @RequestMapping("/authentication/require")
    public String require() {
        return "login";
    }
}
