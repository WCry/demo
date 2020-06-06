package com.zxp.exception;

import java.io.Serializable;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class BusinessException extends BaseException implements Serializable {

    private static final long serialVersionUID = 9102498919545313904L;
    public BusinessException(IResponseEnum iResponseEnum,Object[] args,String message){
         super(iResponseEnum,args,message);
    }
    public BusinessException(IResponseEnum iResponseEnum,Object[] args,String message,Throwable cause){
        super(iResponseEnum,args,message,cause);
    }
}
