package com.leaforbook.webook.db.exception;

/**
 * 数据库配置数据异常，数据缺失或不合法
 * @author xiaoyilin
 *
 */
public class LoadDbConfigDataException extends LoadDbConfigException {
	private static final long serialVersionUID = -3057618783644190369L;
	
	public LoadDbConfigDataException() {
		super("load db config data failure");
	}
	
	public LoadDbConfigDataException(String message) {
		super("load db config data failure:" + message);
	}
	
	public LoadDbConfigDataException(String message,Throwable cause) {
		super("load db config data failure:" + message,cause);
	}
	
	public LoadDbConfigDataException(Throwable cause) {
		super(cause);
	}
}
