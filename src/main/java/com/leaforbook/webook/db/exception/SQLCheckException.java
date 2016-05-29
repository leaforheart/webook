package com.leaforbook.webook.db.exception;

public class SQLCheckException extends Exception {
	private static final long serialVersionUID = 1615015379972994899L;
	
	public SQLCheckException(){}
	
	public SQLCheckException(String sql){
		super("这个SQL语句检测不符合规范，请检查该SQL或包含的SQL片段是否没有在配置文件中配置："+sql);
	}

}
