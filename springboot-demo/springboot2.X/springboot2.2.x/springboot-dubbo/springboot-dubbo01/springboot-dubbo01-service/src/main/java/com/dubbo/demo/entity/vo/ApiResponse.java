package com.dubbo.demo.entity.vo;

import java.io.Serializable;

public class ApiResponse implements Serializable {

    private int code;
    private String msg;
    private Object data;
    private  Boolean success=false;

    private ApiResponse(){

    }

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
        success = false;
    }

    public ApiResponse(Object data) {
        this.data = data;
        success = false;
    }

    public Boolean  isSuccess(){
        return success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
        return "ApiResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
