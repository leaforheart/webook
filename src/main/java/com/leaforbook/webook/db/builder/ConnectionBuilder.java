package com.leaforbook.webook.db.builder;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.leaforbook.webook.db.exception.GetDbConnectionException;
import com.leaforbook.webook.db.exception.LoadDbConfigDataException;
import com.leaforbook.webook.db.exception.LoadDbConfigFileException;

/**
 * 数据库连接构造器
 * @author xiaoyilin
 *
 */
public class ConnectionBuilder {
	
	/**
	 * 构造数据库连接
	 * @param configPath
	 * @return
	 * @throws LoadDbConfigFileException
	 * @throws LoadDbConfigDataException
	 * @throws GetDbConnectionException
	 */
	public static Connection getConnection(String configPath) throws LoadDbConfigFileException, LoadDbConfigDataException, GetDbConnectionException {
		DataSource dataSource = DataSourceBuilder.getDataSource(configPath);
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new GetDbConnectionException(configPath,e);
		}
		return connection;
	}
}
