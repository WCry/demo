package com.zxp.sso.mobile;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * user:zxp
 * Day:2020,07,31
 **/
@Component
public class PhoneCodeFilter implements HandlerInterceptor {
    private List<String> messageList= Arrays.asList("123456","654321");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String message=request.getParameter("phone");
        if (!messageList.contains(message)){
            response.getWriter().write("phone is not exists");
            return false;
        }else {
            return true;
        }
    }
}
