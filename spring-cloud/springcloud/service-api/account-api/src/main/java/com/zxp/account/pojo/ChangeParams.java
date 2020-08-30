package com.zxp.account.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * user:zxp
 * Day:2020,08,16
 **/
@Data
public class ChangeParams {
    private String accountID;
    private BigDecimal money;
}
