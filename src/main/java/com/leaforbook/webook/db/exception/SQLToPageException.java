package com.leaforbook.webook.db.exception;

public class SQLToPageException extends Exception {
	
	private static final long serialVersionUID = 1460643638018850700L;
	public SQLToPageException(){}
	public SQLToPageException(String sql){
		super("普通sql转化为分页查询sql失败，有可能该sql不是一条合法的查询语句："+sql);
	}
}
