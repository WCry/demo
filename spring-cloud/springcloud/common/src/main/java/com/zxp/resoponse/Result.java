package com.zxp.resoponse;

import lombok.Data;

@Data
public class Result<T> {
	
	private int code;
	private String msg;
	private T data;
	private Boolean isSuccess;

	public Boolean getSuccess() {
		return isSuccess;
	}

	public void setSuccess(Boolean success) {
		isSuccess = success;
	}

	/**
	 *  成功时候的调用
	 * */
	public static  <T> Result<T> success(T data){
		return new Result<T>(data);
	}
	
	/**
	 * 失败时候的调用
	 * */
	public static  <T> Result<T> error(CodeMsgEnum codeMsgEnum){
		return new Result<T>(codeMsgEnum);
	}
	
	private Result(T data) {
		this.data = data;
		this.setSuccess(true);
	}
	
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
		this.setSuccess(false);
	}
	
	private Result(CodeMsgEnum codeMsgEnum) {
		if(codeMsgEnum != null) {
			this.code = codeMsgEnum.getCode();
			this.msg = codeMsgEnum.getMsg();
		}
	}
}
