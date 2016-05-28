package com.leaforbook.webook.db.exception;

public class DataSourceCreateException extends Exception {
	private static final long serialVersionUID = -5661849666041821801L;
	public DataSourceCreateException() {}
	public DataSourceCreateException(String dataSourceName,String dataSourceNameConfigPath) {
		super("数据源创建失败，数据源名称："+dataSourceName+",数据源名称配置文件路径为："+dataSourceNameConfigPath);
	}
}
