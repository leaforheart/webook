package com.leaforbook.webook.db.builder;

import java.sql.Connection;
import java.sql.SQLException;

import com.leaforbook.webook.db.constant.DataSourceConstants;
import com.leaforbook.webook.db.exception.ConnectionBuilderException;
import com.leaforbook.webook.db.exception.DataSourceCreateException;

public class BasicConnectionBuilder implements ConnectionBuilder {
	
	private String defaultDataSourceNameConfigPath;
	private String defaultDataSourceName;
	
	public BasicConnectionBuilder() {
		setDefaultDataSourceNameConfigPath();
		setDefaultDataSourceName();
	}

	@Override
	public void setDefaultDataSourceNameConfigPath() {
		defaultDataSourceNameConfigPath = DataSourceConstants.DEFAULT_DATASOURCENAME_CONFIGPATH;
	}

	@Override
	public void setDefaultDataSourceName() {
		defaultDataSourceName = DataSourceConstants.DEFAULT;
	}

	@Override
	public Connection getConnection() throws ConnectionBuilderException {
		DataSourceFactory factory = new BasicDataSourceFactory(defaultDataSourceName,defaultDataSourceNameConfigPath);
		Connection connection = null;
		try {
			connection = factory.getDataSource().getConnection();
		} catch (SQLException e) {
			throw new ConnectionBuilderException(e.toString());
		} catch (DataSourceCreateException e) {
			throw new ConnectionBuilderException(defaultDataSourceName,defaultDataSourceNameConfigPath);
		}
		return connection;
	}

	@Override
	public Connection getConnection(String dataSourceName) throws ConnectionBuilderException {
		DataSourceFactory factory = new BasicDataSourceFactory(dataSourceName,defaultDataSourceNameConfigPath);
		Connection connection = null;
		try {
			connection = factory.getDataSource().getConnection();
		} catch (SQLException e) {
			throw new ConnectionBuilderException(e.toString());
		} catch (DataSourceCreateException e) {
			throw new ConnectionBuilderException(dataSourceName,defaultDataSourceNameConfigPath);
		}
		return connection;
	}

	@Override
	public Connection getConnection(String dataSourceName,String dataSourceNameConfigPath) throws ConnectionBuilderException {
		DataSourceFactory factory = new BasicDataSourceFactory(dataSourceName,dataSourceNameConfigPath);
		Connection connection = null;
		try {
			connection = factory.getDataSource().getConnection();
		} catch (SQLException e) {
			throw new ConnectionBuilderException(e.toString());
		} catch (DataSourceCreateException e) {
			throw new ConnectionBuilderException(dataSourceName,dataSourceNameConfigPath);
		}
		return connection;
	}

}
