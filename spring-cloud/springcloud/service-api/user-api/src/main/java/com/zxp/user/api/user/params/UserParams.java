package com.zxp.user.api.user.params;

import lombok.Data;

/**
 * 用户注册参数
 */
@Data
public class UserParams {
    //用户名
    private String username;
    //密码
    private String password;
    //注册手机号
    private String phone;
    //昵称
    private String nickName;
    //真实姓名
    private String name;
}
