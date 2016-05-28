package com.leaforbook.webook.db.exception;

public class ConnectionBuilderException extends Exception {
	private static final long serialVersionUID = 8946546702681697760L;
	public ConnectionBuilderException() {}
	public ConnectionBuilderException(String dataSourceName,String dataSourceNameConfigPath) {
		super("Connection获取失败，原因是DataSource创建失败，数据源名称："+dataSourceName+",数据源名称配置文件路径为："+dataSourceNameConfigPath);
	}
	public ConnectionBuilderException(String e) {
		super("Connection获取失败，原因是发生了SQLException，即数据库连接错误:"+e);
	}
}
