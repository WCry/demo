package com.zxp.handler;

import com.zxp.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
@ControllerAdvice
@ConditionalOnWebApplication
@ConditionalOnMissingBean(UnifiedExceptionHandler.class)
public class UnifiedExceptionHandler {
    private final static String ENV_PROD = "prod";

    @Value("${spring.profiles.active}")
    private String profile;

    public String getMessage(BaseException e) {
        String code = "response." + e.getResponseEnum().getCode();
        String message = e.getResponseEnum().getMessage();
        return message;
    }
}
