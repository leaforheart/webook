package com.leaforbook.webook.db.exception;

/**
 * 由于数据库配置文件引起的异常
 * @author xiaoyilin
 *
 */
public class LoadDbConfigException extends WdbException {
	private static final long serialVersionUID = 2918279596816088007L;
	
	public LoadDbConfigException() {
		super("load db config failure");
	}
	
	public LoadDbConfigException(String message) {
		super("load db config failure:" + message);
	}
	
	public LoadDbConfigException(String message,Throwable cause) {
		super("load db config failure:" + message,cause);
	}
	
	public LoadDbConfigException(Throwable cause) {
		super(cause);
	}

}
