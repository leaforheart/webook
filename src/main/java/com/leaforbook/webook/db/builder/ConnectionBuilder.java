package com.leaforbook.webook.db.builder;

import java.sql.Connection;

import com.leaforbook.webook.db.exception.ConnectionBuilderException;

public interface ConnectionBuilder {
	void setDefaultDataSourceNameConfigPath();
	void setDefaultDataSourceName();
	Connection getConnection() throws ConnectionBuilderException;
	Connection getConnection(String dataSourceName) throws ConnectionBuilderException;
	Connection getConnection(String dataSourceName,String dataSourceNameConfigPath) throws ConnectionBuilderException;
}
