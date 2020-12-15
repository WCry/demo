package com.zxp.controller;

import com.zxp.wrapper.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangxuepei
 * @since 3.0
 * 错误处理Controller
 */
@Slf4j
@RestController
public class HttpErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @ResponseBody
    @RequestMapping(path  = ERROR_PATH )
    public BaseResult error(HttpServletRequest request, HttpServletResponse response){
        log.info("访问/error" + "  错误代码："  + response.getStatus());
        BaseResult result = new BaseResult(BaseResult.RESULT_FAIL,"HttpErrorController error:"+response.getStatus());
        return result;
    }
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
