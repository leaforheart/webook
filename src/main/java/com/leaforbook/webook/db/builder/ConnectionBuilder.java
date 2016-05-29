package com.leaforbook.webook.db.builder;

import java.sql.Connection;

import com.leaforbook.webook.db.exception.ConnectionBuilderException;

public abstract class ConnectionBuilder {
	public ConnectionBuilder() {
		setDefaultDataSourceNameConfigPath();
		setDefaultDataSourceName();
	}
	protected abstract void setDefaultDataSourceNameConfigPath();
	protected abstract void setDefaultDataSourceName();
	public abstract Connection getConnection() throws ConnectionBuilderException;
	public abstract Connection getConnection(String dataSourceName) throws ConnectionBuilderException;
	public abstract Connection getConnection(String dataSourceName,String dataSourceNameConfigPath) throws ConnectionBuilderException;
}
