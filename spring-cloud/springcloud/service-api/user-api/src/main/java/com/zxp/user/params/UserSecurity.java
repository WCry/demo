package com.zxp.user.params;

/**
 * 用户安全验证有关信息
 */
public interface UserSecurity extends UserAccount{
    //密码
    String getPassword();
    //密码
    String getPhone();
}
