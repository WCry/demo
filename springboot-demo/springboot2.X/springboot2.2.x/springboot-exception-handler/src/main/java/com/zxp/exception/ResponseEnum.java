package com.zxp.exception;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.util.Assert;

/**
 * @author zhangxuepei
 * @since 3.0
 */

public enum ResponseEnum implements BusinessExceptionAssert {
    BAD_LICENCE_TYPE(7001, "Bad Licence Type"),
    LICENCE_NOT_FOUND(7002,"Licence is not found");
    private int code;
    private String message;
    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
