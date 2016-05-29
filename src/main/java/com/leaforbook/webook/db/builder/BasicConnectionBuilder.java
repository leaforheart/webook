package com.leaforbook.webook.db.builder;

import java.sql.Connection;
import java.sql.SQLException;

import com.leaforbook.webook.db.constant.DataSourceConstants;
import com.leaforbook.webook.db.exception.ConnectionBuilderException;
import com.leaforbook.webook.db.exception.DataSourceCreateException;

public class BasicConnectionBuilder extends ConnectionBuilder {
	
	protected String defaultDataSourceNameConfigPath;
	protected String defaultDataSourceName;

	@Override
	protected void setDefaultDataSourceNameConfigPath() {
		defaultDataSourceNameConfigPath = DataSourceConstants.DEFAULT_DATASOURCENAME_CONFIGPATH;
	}

	@Override
	protected void setDefaultDataSourceName() {
		defaultDataSourceName = DataSourceConstants.DEFAULT;
	}

	@Override
	public Connection getConnection() throws ConnectionBuilderException {
		DataSourceBuilder factory = new BasicDataSourceBuilder(defaultDataSourceName,defaultDataSourceNameConfigPath);
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
		DataSourceBuilder factory = new BasicDataSourceBuilder(dataSourceName,defaultDataSourceNameConfigPath);
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
		DataSourceBuilder factory = new BasicDataSourceBuilder(dataSourceName,dataSourceNameConfigPath);
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
