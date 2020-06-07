package com.zxp.exception;

/**
 * @author zhangxuepei
 * @since 3.0
 * 将错误码异常信息的枚举和断言加到一起进行使用
 * 错误类型的枚举同时具有断言判断的功能
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
