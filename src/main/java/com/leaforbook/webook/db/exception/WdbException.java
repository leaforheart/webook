package com.leaforbook.webook.db.exception;

/**
 * 所有在webook-util组件中发生的检查性异常
 * @author xiaoyilin
 *
 */
public class WdbException extends Exception {
	private static final long serialVersionUID = -6847136379839768445L;

	public WdbException() {}
	
	public WdbException(String message) {
		super(message);
	}
	
	public WdbException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public WdbException(Throwable cause) {
		super(cause);
	}
		
}
