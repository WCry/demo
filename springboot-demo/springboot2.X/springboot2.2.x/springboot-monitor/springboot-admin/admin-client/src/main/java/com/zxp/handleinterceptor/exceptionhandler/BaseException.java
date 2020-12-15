package com.zxp.handleinterceptor.exceptionhandler;

import lombok.Data;

import java.text.MessageFormat;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String code;
    /**
     * 枚举对象默认的 异常信息描述
     */
    private String message;
    /**
     * 异常消息参数
     */
    protected Object[] args;

    private IResponseEnum responseEnum;

    public BaseException(String code,String message){
        super(code);
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @param responseEnum  异常枚举对象
     */
    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getCode());
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.responseEnum = responseEnum;
    }

    /**
     *
     * @param responseEnum 异常枚举对象
     * @param cause  异常cause
     */
    public BaseException(IResponseEnum responseEnum,Throwable cause) {
        super(responseEnum.getCode(),cause);
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.responseEnum = responseEnum;
    }

    /**
     *
     * @param responseEnum  异常枚举对象
     * @param args  占位符参数
     */
    public BaseException(IResponseEnum responseEnum,Object[] args) {
        super(responseEnum.getCode());
        this.code = responseEnum.getCode();
        this.responseEnum = responseEnum;
        this.args = args;
        try {
            this.message = MessageFormat.format(responseEnum.getMessage(),args);
        }catch (IllegalArgumentException e){
        }

    }

    /**
     *
     * @param responseEnum    异常枚举对象
     * @param args  占位符参数
     * @param cause  异常cause
     */
    public BaseException(IResponseEnum responseEnum,Object[] args,Throwable cause) {
        super(responseEnum.getCode(), cause);
        this.code = responseEnum.getCode();
        this.responseEnum = responseEnum;
        this.args = args;
        try {
            this.message = MessageFormat.format(responseEnum.getMessage(),args);
        }catch (IllegalArgumentException e){
        }
    }
}
