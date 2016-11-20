package com.leaforbook.webook.db;

import java.sql.Connection;

import com.leaforbook.webook.db.exception.GetDbConnectionException;
import com.leaforbook.webook.db.exception.LoadDbConfigDataException;
import com.leaforbook.webook.db.exception.LoadDbConfigFileException;

public interface ConnectionPool {
	Connection getConnection() throws LoadDbConfigFileException, LoadDbConfigDataException, GetDbConnectionException;
	void setConfigPath(String configPath);
}
