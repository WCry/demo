package com.zxp.account.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * user:zxp
 * Day:2020,08,16
 **/
@Data
@AllArgsConstructor
public class ChangeParams {
    private String accountID;
    private BigDecimal money;
}
