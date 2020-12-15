package com.zxp.handleinterceptor.exceptionhandler;

import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @author zhangxuepei
 * @since 3.0
 */

public interface CommonExceptionAssert extends IResponseEnum, Assert {

    /**
     * 创建异常
     *
     * @param param 异常对象
     *
     * @return
     */
    @Override
    default BaseException newException(Object param) {
        return null;
    }


    /**
     * 创建异常
     *
     * @param param 异常对象
     * @param args 占位符信息
     *
     * @return
     */
    @Override
    default BaseException newException(Object param, Object... args) {
        return null;
    }


    /**
     * 断言对象 param.
     * 如果对象为 null ，则抛出异常
     *
     * @param param 待判断的对象
     */
    default void assertNotEmpty(Collection<?> param) {
        if (CollectionUtils.isEmpty(param)) {
            throw newException(null);
        }
    }

    /**
     * 断言对象 param.
     * 如果对象为 null ，则抛出异常
     * 异常占位符:args 支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param param 待判断的对象
     * @param args message占位符对应的参数列表
     */
    default void assertNotEmpty(Collection<?> param, Object... args) {
        if (CollectionUtils.isEmpty(param)) {
            throw newException(null, args);
        }
    }
}
