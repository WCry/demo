package com.zxp.exception;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public interface Assert {
    BaseException newException(Object... args);
    BaseException newException(Throwable t,Object... args);
    default void assertNotNull(Object obj){
        if(obj==null){
            throw newException(obj);
        }
    }
    default void assertNotNull(Object obj,Object... args){
        if(obj==null){
            throw newException(args);
        }
    }
}
