package com.leaforbook.webook.db.exception;

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
