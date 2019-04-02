package com.zhulong.framework.common.exception;

/**
 * 系统异常
 * @author xxc
 * @time 2019-1-9 14:51
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = -4596676578310239059L;

	private String code; //异常代码

	private String[] extra; //异常信息

	
	public SystemException(String code) {
		super(code);
		this.code = code;
	}
	
	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String code, String... extra) {
		super(code);
		this.code = code;
		this.extra = extra;
	}

	public SystemException(String code, Throwable cause) {
		super(code, cause);
		this.code = code;
	}

	public SystemException(String code, Throwable cause, String... extra) {
		super(code, cause);
		this.code = code;
		this.extra = extra;
	}

	public String getCode() {
		return code;
	}

	public String[] getExtra() {
		return extra;
	}
}
