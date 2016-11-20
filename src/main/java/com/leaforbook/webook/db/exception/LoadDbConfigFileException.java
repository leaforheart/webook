package com.leaforbook.webook.db.exception;

public class LoadDbConfigFileException extends LoadDbConfigException {

	private static final long serialVersionUID = 3997107486637414318L;
	
	public LoadDbConfigFileException() {
		super("load db config file failure");
	}
	
	public LoadDbConfigFileException(String message) {
		super("load db config file failure:" + message);
	}
	
	public LoadDbConfigFileException(String message,Throwable cause) {
		super("load db config file failure:" + message,cause);
	}
	
	public LoadDbConfigFileException(Throwable cause) {
		super(cause);
	}

}
