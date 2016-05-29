package com.leaforbook.webook.db.exception;

public class SQLToCountException extends Exception {
	private static final long serialVersionUID = -425404916227831388L;
	
	public SQLToCountException() {}
	
	public SQLToCountException(String sql) {
		super("普通sql转化为查询记录数sql失败，有可能该sql不是一条合法的查询语句："+sql);
	}

}
