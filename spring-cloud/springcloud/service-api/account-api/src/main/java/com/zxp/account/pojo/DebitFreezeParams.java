package com.zxp.account.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * user:zxp
 * Day:2020,08,16
 * 对于与消费 进行先冻结再结账
 **/
@Data
public class DebitFreezeParams {
    private String srcAccountID;
    private String targetAccountID;
    private BigDecimal money;
}
