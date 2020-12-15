package com.zxp.handleinterceptor.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangxuepei
 * @since 3.0
 */

@AllArgsConstructor
public enum BusinessExceptionEnum  {

    ACCOUNT_IS_NOT_EXIST("B_A_1000","没有查到用户ID为:''{0}''的账户信息"),
    ACCOUNT_IS_NOT_ENOUGH("B_A_1001","用户ID为:''{0}''的账户余额''{1}''不足抵扣消费金额为''{2}''"),
    ACCOUNT_LESSEN_FAIL("B_A_1002","用户ID为:''{0}''的账户，扣款失败"),

    WORK_ID_CHECK_FAIL("B_W_1001","worker Id can't be greater than ''{0}'' or less than 0");


    private String code;
    /**
     * 返回消息
     */
    private String message;
}
