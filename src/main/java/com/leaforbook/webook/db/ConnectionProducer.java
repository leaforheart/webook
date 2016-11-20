package com.leaforbook.webook.db;

import java.sql.Connection;

import com.leaforbook.webook.db.builder.ConnectionBuilder;
import com.leaforbook.webook.db.constant.DataSourceConstants;
import com.leaforbook.webook.db.exception.GetDbConnectionException;
import com.leaforbook.webook.db.exception.LoadDbConfigDataException;
import com.leaforbook.webook.db.exception.LoadDbConfigFileException;

/**
 * 连接池实现类，实现了ConnectionPool接口
 * @author xiaoyilin
 *
 */
public class ConnectionProducer implements ConnectionPool {
	
	private String configPath;

	/**
	 * 获取数据库配置文件路径
	 * @return
	 */
	public String getConfigPath() {
		return configPath;
	}

	/**
	 * 设置数据库连接配置文件路径，配置文件为properties文件
	 * @param configPath
	 */
	@Override
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
	
	/**
	 * 无参构造器，默认设置数据库配置文件路径为db.properties
	 */
	public ConnectionProducer() {
		this.configPath = DataSourceConstants.DEFAULT_DATASOURCENAME_CONFIGPATH;
	}
	
	/**
	 * 可以初始化数据库配置文件路径的构造器
	 * @param configPath
	 */
	public ConnectionProducer(String configPath) {
		this.configPath = configPath;
	}

	/**
	 * 获取数据库连接
	 * @return
	 * @throws LoadDbConfigFileException
	 * @throws LoadDbConfigDataException
	 * @throws GetDbConnectionException
	 */
	@Override
	public Connection getConnection() throws LoadDbConfigFileException, LoadDbConfigDataException, GetDbConnectionException {
		Connection conn = ConnectionBuilder.getConnection(configPath);
		return conn;
	}

}
