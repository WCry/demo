package com.zxp.exception;


import com.zxp.resoponse.CodeMsgEnum;
import com.zxp.resoponse.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 自定义全局异常拦截器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)//拦截所有异常
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        if(e instanceof GlobalException) {
            GlobalException ex = (GlobalException)e;
            return Result.error(ex.getCodeMsgEnum());
        }else if(e instanceof BindException) {
            BindException ex = (BindException)e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsgEnum.BIND_ERROR.fillArgs(msg));
        }else {
            return Result.error(CodeMsgEnum.SERVER_ERROR);
        }
    }
}
