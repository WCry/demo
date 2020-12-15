package com.zxp.wrapper;

import java.io.Serializable;

/**
 * @author zhangxuepei
 * @since 3.0
 * 返回信息包括错误信息
 */
public class BaseResult implements Serializable {

    public static final int RESULT_FAIL = 0;
    public static final int RESULT_SUCCESS = 1;
    private static final long serialVersionUID = 1L;
    //返回代码
    private Integer code;

    //返回消息
    private String message;

    //返回对象
    private Object data;

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.data = object;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
