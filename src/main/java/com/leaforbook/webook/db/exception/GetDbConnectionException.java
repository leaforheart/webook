package com.leaforbook.webook.db.exception;

/**
 * 获取数据库连接异常，可能是因为配置的数据库资源不存在，或数据库资源暂时不可用
 * @author xiaoyilin
 *
 */
public class GetDbConnectionException extends WdbException {
	private static final long serialVersionUID = -3443696107817672598L;
	
	public GetDbConnectionException() {
		super("get db connection failure");
	}
	
	public GetDbConnectionException(String message) {
		super("get db connection failure:" + message);
	}
	
	public GetDbConnectionException(String message,Throwable cause) {
		super("get db connection failure:" + message,cause);
	}
	
	public GetDbConnectionException(Throwable cause) {
		super(cause);
	}

}
