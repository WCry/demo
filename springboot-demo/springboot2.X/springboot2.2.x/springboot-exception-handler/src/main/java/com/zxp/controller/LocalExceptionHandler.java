package com.zxp.controller;

import com.zxp.entity.vo.ApiResponse;
import com.zxp.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangxuepei
 */
@RestController
public class LocalExceptionHandler {

    //todo:当前异常需要局部处理 我不一样 不需要全局处理
    @RequestMapping("/getLocalExceptionHandle")
    public ApiResponse getMyException() throws MyException {
        String str = null;
        if (str == null) {
            throw new MyException();
        }
        return ApiResponse.ok();
    }
    /**
     * todo:异常处理
     */
    @ExceptionHandler(value = MyException.class)
    public Object myExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        System.out.println("=====================局部异常处理了===================================");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("msg", "抛出自定义异常");
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
