package com.zxp.soo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * user:zxp
 * Day:2020,05,31
 **/
@Controller
public class HtmlCotroller {
//    @Autowired
//    private  CsrfTokenRepository tokenRepository;


    @RequestMapping(value = "/")
    public ModelAndView index(HttpServletRequest httpRequest) {
        ModelAndView modelAndView = new ModelAndView();
//        CsrfToken csrfToken= tokenRepository.loadToken(httpRequest);
//        modelAndView.addObject(csrfToken);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
