package com.zxp.exception;


import com.zxp.resoponse.CodeMsgEnum;

/**
 * 自定义全局异常类
 */
public class GlobalException extends RuntimeException {

    private CodeMsgEnum codeMsgEnum;

    public GlobalException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum.toString());
        this.codeMsgEnum = codeMsgEnum;
    }

    public CodeMsgEnum getCodeMsgEnum() {
        return codeMsgEnum;
    }
}
