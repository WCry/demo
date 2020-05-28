package com.lhj.miaosha.vo;

import com.lhj.miaosha.validator.IsMobile;

import javax.validation.constraints.NotNull;

/**
 * @author ：LHJ
 * @date ：2019/7/25 18:07
 * @description：${description}
 */
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;//@IsMobile 假如手机号码格式错误将抛出绑定异常
    @NotNull
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
