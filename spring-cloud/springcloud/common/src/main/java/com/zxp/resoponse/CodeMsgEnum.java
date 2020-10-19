package com.zxp.resoponse;

import lombok.Data;

@Data
public class CodeMsgEnum {

    private int code;
    private String msg;

    //通用的错误码
    public static CodeMsgEnum SUCCESS = new CodeMsgEnum(0, "成功");
    public static CodeMsgEnum SERVER_ERROR = new CodeMsgEnum(500100, "服务端异常");
    public static CodeMsgEnum BIND_ERROR = new CodeMsgEnum(500101, "参数校验异常：%s");
    public static CodeMsgEnum ACCESS_LIMIT_REACHED= new CodeMsgEnum(500104, "访问高峰期，请稍等！");
    //登录模块 5002XX
    public static CodeMsgEnum SESSION_ERROR = new CodeMsgEnum(500210, "Session不存在或者已经失效");
    public static CodeMsgEnum PASSWORD_EMPTY = new CodeMsgEnum(500211, "登录密码不能为空");
    public static CodeMsgEnum MOBILE_EMPTY = new CodeMsgEnum(500212, "手机号不能为空");
    public static CodeMsgEnum MOBILE_ERROR = new CodeMsgEnum(500213, "手机号格式错误");
    public static CodeMsgEnum MOBILE_NOT_EXIST = new CodeMsgEnum(500214, "手机号不存在");
    public static CodeMsgEnum PASSWORD_ERROR = new CodeMsgEnum(500215, "密码错误");
    public static CodeMsgEnum PRIMARY_ERROR = new CodeMsgEnum(500216, "主键冲突");


    public static CodeMsgEnum ORDER_NOT_EXIST = new CodeMsgEnum(500400, "订单不存在");

    public static CodeMsgEnum SECKILL_OVER = new CodeMsgEnum(500500, "商品已经秒杀完毕");
    public static CodeMsgEnum REPEATE_SECKILL = new CodeMsgEnum(500501, "不能重复秒杀");

    private CodeMsgEnum() {
    }

    private CodeMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回带参数的错误码
     * @param args
     * @return
     */
    public CodeMsgEnum fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsgEnum(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }


}
