package com.zxp.exception;

import java.io.Serializable;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public abstract class BaseException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 9102498919545313904L;
    private IResponseEnum responseEnum;
    public BaseException(IResponseEnum iResponseEnum, Object[] args, String message){
        super(message);
        this.responseEnum=iResponseEnum;
    }
    public BaseException(IResponseEnum iResponseEnum, Object[] args, String message,Throwable cause){
        super(message,cause);
    }

    public IResponseEnum getResponseEnum() {
        return responseEnum;
    }
}
