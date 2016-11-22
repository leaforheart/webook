package com.leaforbook.webook.db;

import java.sql.Connection;

import com.leaforbook.webook.db.exception.GetDbConnectionException;
import com.leaforbook.webook.db.exception.LoadDbConfigDataException;
import com.leaforbook.webook.db.exception.LoadDbConfigFileException;

/**
 * 连接池接口
 * @author xiaoyilin
 *
 */
public interface ConnectionPool {
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws LoadDbConfigFileException
	 * @throws LoadDbConfigDataException
	 * @throws GetDbConnectionException
	 */
	Connection getConnection() throws LoadDbConfigFileException, LoadDbConfigDataException, GetDbConnectionException;
	
	/**
	 * 设置数据库连接配置文件路径，配置文件为properties文件
	 * @param configPath
	 */
	void setConfigPath(String configPath);
}
