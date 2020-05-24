package com.lhj.miaosha.controller;


import com.lhj.miaosha.result.Result;
import com.lhj.miaosha.service.MiaoshaUserService;
import com.lhj.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author ：LHJ
 * @date ：2019/7/24 13:04
 * @description：${description}
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    MiaoshaUserService userService;
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response , @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        String token = userService.login(response,loginVo);
        return Result.success(token);
    }

}
