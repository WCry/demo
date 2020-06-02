package com.zxp.soo.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * user:zxp
 * Day:2020,05,31
 **/
@Controller
public class HtmlCotroller {

    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletRequest httpRequest) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/update")
    public String update(String httpRequest) {
        return httpRequest;
    }
}
