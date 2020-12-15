package com.zxp.handleinterceptor;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zhangxuepei
 * @since 3.0
 * 在Response被写入到 Http的返回流当中
 * 统一处理 ResponseBody的包装
 */
@ControllerAdvice
public class BaseResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //判断是否是流式处理
        if(MediaType.APPLICATION_OCTET_STREAM.equals(mediaType)){

        }
        //判断返回类型
        if(object instanceof byte[]){

        }
        return null;
    }
}
