package com.zxp.controller;

import com.zxp.entity.vo.ApiResponse;
import com.zxp.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GlobalExceptionController {

    @RequestMapping("/ajaxerror")
    public ModelAndView ajaxerror() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ajaxerror");
        return modelAndView;
    }

    @RequestMapping("/getOK")
    @ResponseBody
    public ApiResponse getAjaxerror() {
        int a = 1 / 0;
        return ApiResponse.ok();
    }

    /*
       todo：Controller 层将异常抛出 有异常处理进行处理掉 我需要全局处理
     */
    @RequestMapping("/getGlobalException")
    @ResponseBody
    public ApiResponse getMyException() throws MyException {
        String str = null;
        if (str == null) {
            throw new MyException();
        }
        return ApiResponse.ok();
    }

}
