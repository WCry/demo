package com.zxp.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Data
public class UserConsume {
    private String userID;
    private BigDecimal count;
}
