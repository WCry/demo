package com.zxp.demo.flink.entry;

import java.io.Serializable;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class LoginWarning implements Serializable {
    private String userId;
    private String type;
    private String ip;

    public LoginWarning() {
    }

    public LoginWarning(String userId, String type, String ip) {
        this.userId = userId;
        this.type = type;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return userId + "," + "ip" + "," + type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
