package com.zxp.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/authentication/require")
    public ModelAndView require() {
        return new ModelAndView("ftl/login");
    }
    @GetMapping("/index")
    public String reqdsuire() {
        return "dsad";
    }
}
