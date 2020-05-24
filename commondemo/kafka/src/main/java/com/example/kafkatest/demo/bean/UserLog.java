package com.example.kafkatest.demo.bean;

import java.io.Serializable;

public class UserLog implements Serializable {
    private String username;
    private String userid;
    private String state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "username='" + username + '\'' +
                ", userid='" + userid + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
