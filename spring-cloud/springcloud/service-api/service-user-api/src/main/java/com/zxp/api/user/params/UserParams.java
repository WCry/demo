package com.zxp.api.user.params;

import lombok.Data;

/**
 * 用户注册参数
 */
@Data
public class UserParams {

    private String username;//用户名

    private String password;//密码

    private String phone;//注册手机号

    private String email;//注册邮箱

    private String sourceType;//会员来源：1:PC，2：H5，3：Android，4：IOS

    private String nickName;//昵称

    private String name;//真实姓名
}
