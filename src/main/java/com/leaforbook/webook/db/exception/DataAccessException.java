package com.leaforbook.webook.db.exception;

public class DataAccessException extends Exception {

	private static final long serialVersionUID = -9158856792514364623L;
	public DataAccessException() {}
	public DataAccessException(String methodName,String sql) {
		super("访问"+methodName+"方法时发生错误，原因是这个SQL语句检测不符合规范，请检查该SQL或包含的SQL片段是否没有在配置文件中配置："+sql);
	}
	public DataAccessException(String methodName) {
		super("访问"+methodName+"方法时发生SQLException异常");
	}
	public DataAccessException(String methodName,String sql,boolean isPage) {
		super("访问"+methodName+"方法时发生错误，原因可能是该SQL不是合法的查询语句");
	}

}
