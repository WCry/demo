package com.zxp.demo.flink.entry;

import java.io.Serializable;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class LoginEvent implements Serializable {
    private String userId;//用户ID
    private String ip;//登录IP
    private String type;//登录类型

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LoginEvent() {
    }
    public LoginEvent(String userId, String ip, String type) {
        this.userId = userId;
        this.ip = ip;
        this.type = type;
    }
}
