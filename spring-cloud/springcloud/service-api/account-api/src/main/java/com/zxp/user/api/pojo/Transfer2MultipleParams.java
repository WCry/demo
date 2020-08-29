package com.zxp.user.api.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * user:zxp
 * Day:2020,08,16
 * 向多个账户进行转账
 * 发红包等一些业务
 **/
@Data
public class Transfer2MultipleParams {
    private String srcAccountID;
    private Map<String,BigDecimal> targetMultiple;
}
