package com.leaforbook.webook.db.builder;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;

import com.leaforbook.webook.db.constant.DBCPConstants;
import com.leaforbook.webook.db.exception.DataSourceCreateException;
import com.leaforbook.webook.util.PropertiesReader;

public class BasicDataSourceFactory extends DataSourceFactory {

	public BasicDataSourceFactory(String dataSourceName,String dataSourceNameConfigPath) {
		super(dataSourceName, dataSourceNameConfigPath);
	}

	@Override
	protected DataSource loadProperties(PropertiesReader reader) throws DataSourceCreateException {
		
		BasicDataSource bds = new BasicDataSource();
		
		String driverName = reader.getString(DBCPConstants.DRIVERCLASSNAME);
		String url = reader.getString(DBCPConstants.URL);
		String username = reader.getString(DBCPConstants.USERNAME);
		String password = reader.getString(DBCPConstants.PASSWORD);
		
		if(StringUtils.isEmpty(driverName)||StringUtils.isEmpty(url)||StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
			throw new DataSourceCreateException(dataSourceName,dataSourceNameConfigPath);
		}
		
		bds.setDriverClassName(driverName);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		
		bds.setInitialSize(reader.getInt(DBCPConstants.INITTIALSIZE,0));
		bds.setMaxActive(reader.getInt(DBCPConstants.MAXTOTAL, 8));
		bds.setMaxIdle(reader.getInt(DBCPConstants.MAXIDLE, 8));
		bds.setMinIdle(reader.getInt(DBCPConstants.MINIDLE,0));
		bds.setMaxWait(reader.getInt(DBCPConstants.MAXWAITMILLI, -1));
		
		bds.setDefaultAutoCommit(reader.getBoolean(DBCPConstants.DEFAULTAUTOCOMMIT, false));
		
		bds.setPoolPreparedStatements(reader.getBoolean(DBCPConstants.POOLPREPAREDSTATEMENTS, false));
		bds.setMaxOpenPreparedStatements(reader.getInt(DBCPConstants.MAXOPENPREPAREDSTATEMENTS, -1));
		
		return bds;
	}

}
