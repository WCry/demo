package com.zxp.user.params.update;

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
public class UserSecurityParams implements UserSecurity {
    //用户名
    private String userAccount;
    //密码
    private String password;
    //注册手机号
    private String phone;
}
