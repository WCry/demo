package com.zxp.user.params;

/**
 * 用户注册基本信息
 * 包含用户的基本信息和用户中的敏感信息
 */
public interface UserBaseRegister extends UserBase {
    String getName();
    String getPhone();
}
