package com.zxp.handleinterceptor.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangxuepei
 * @since 3.0
 * 统一异常处理 通过异常类进行处理
 * 处理401 404 等ErrorController 处理异常路径
 */
@ControllerAdvice
@Slf4j
public class ExceptionResponseHandler {
    @ResponseBody
    /**
     * @ExceptionHandler 放在具体的
     */
    @ExceptionHandler(NullPointerException.class)
    public Object globalException(HttpServletResponse response, NullPointerException ex) {
        log.info("GlobalExceptionHandler...");
        log.info("错误代码：" + response.getStatus());
        return null;
    }
}
