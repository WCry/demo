package com.zxp.handleinterceptor.exceptionhandler;

import java.io.Serializable;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public interface IResponseEnum extends Serializable {

    String getCode();
    String getMessage();
    default String asString(){
        return "(\""+getCode()+"\",\""+getMessage()+"\")";
    }
}
