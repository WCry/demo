package com.zxp.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class UserTel {
    @NotEmpty
    private String iphone;
    @NotEmpty
    private String telephone;

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
