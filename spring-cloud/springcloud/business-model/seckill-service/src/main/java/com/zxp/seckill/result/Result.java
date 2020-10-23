package com.zxp.seckill.result;

import com.zxp.resoponse.CodeMsgEnum;

public class Result<T> {
	
	private int code;
	private String msg;
	private T data;
	
	/**
	 *  成功时候的调用
	 * */
	public static  <T> Result<T> success(T data){
		return new Result<T>(data);
	}
	
	/**
	 *  失败时候的调用
	 * */
	public static  <T> Result<T> error(CodeMsgEnum codeMsgEnum){
		return new Result<T>(codeMsgEnum);
	}
	
	private Result(T data) {
		this.data = data;
	}
	
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	private Result(CodeMsgEnum codeMsgEnum) {
		if(codeMsgEnum != null) {
			this.code = codeMsgEnum.getCode();
			this.msg = codeMsgEnum.getMsg();
		}
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
