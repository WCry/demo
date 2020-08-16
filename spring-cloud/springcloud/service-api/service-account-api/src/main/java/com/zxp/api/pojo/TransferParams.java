package com.zxp.api.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * user:zxp
 * Day:2020,08,16
 **/
@Data
public class TransferParams {
    private String srcAccountID;
    private String targetAccountID;
    private BigDecimal money;
}
