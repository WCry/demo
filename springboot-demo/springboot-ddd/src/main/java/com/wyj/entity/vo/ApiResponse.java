package com.wyj.entity.vo;

import java.io.Serializable;


public class ApiResponse implements Serializable {

    private String msg;
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(Object data) {
        this.data = data;
    }

    public ApiResponse(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public static ApiResponse success(Object date) {
        return new ApiResponse(date);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
