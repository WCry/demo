package com.zxp.exception;


import java.text.MessageFormat;

/**
 * @author zhangxuepei
 * @since 3.0
 * 业务异常  某一个业务异常
 */
public interface BusinessExceptionAssert extends Assert, IResponseEnum {
    @Override
   default  BaseException newException(Object... args) {
        String message= MessageFormat.format(this.getMessage(),args);
        return new BusinessException(this,args,message);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String message= MessageFormat.format(this.getMessage(),args);
        return new BusinessException(this,args,message,t);
    }

}
