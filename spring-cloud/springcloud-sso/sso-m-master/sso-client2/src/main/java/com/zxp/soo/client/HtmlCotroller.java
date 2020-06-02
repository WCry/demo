package com.zxp.soo.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * user:zxp
 * Day:2020,05,31
 **/
@Controller
public class HtmlCotroller {

    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletRequest httpRequest) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String update() {
       // System.out.println(httpRequest);
        return "url";
    }
}
