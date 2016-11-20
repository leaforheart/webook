package com.leaforbook.webook.db;

import java.sql.Connection;

import com.leaforbook.webook.db.builder.ConnectionBuilder;
import com.leaforbook.webook.db.constant.DataSourceConstants;
import com.leaforbook.webook.db.exception.GetDbConnectionException;
import com.leaforbook.webook.db.exception.LoadDbConfigDataException;
import com.leaforbook.webook.db.exception.LoadDbConfigFileException;

public class ConnectionProducer implements ConnectionPool {
	
	private String configPath;

	public String getConfigPath() {
		return configPath;
	}

	@Override
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
	
	public ConnectionProducer() {
		this.configPath = DataSourceConstants.DEFAULT_DATASOURCENAME_CONFIGPATH;
	}
	
	public ConnectionProducer(String configPath) {
		this.configPath = configPath;
	}

	@Override
	public Connection getConnection() throws LoadDbConfigFileException, LoadDbConfigDataException, GetDbConnectionException {
		Connection conn = ConnectionBuilder.getConnection(configPath);
		return conn;
	}

}
