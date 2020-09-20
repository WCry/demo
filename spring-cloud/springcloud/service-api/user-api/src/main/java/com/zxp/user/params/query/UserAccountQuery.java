package com.zxp.user.params.query;

import com.zxp.user.params.UserAccount;
import com.zxp.user.params.UserSecurity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户安全验证
 * 用户账户验证基本信息
 * 构造函数按照参数的顺序
 */
@Data
@AllArgsConstructor
public class UserAccountQuery implements UserAccount {
    //用户名
    private String userAccount;
}
