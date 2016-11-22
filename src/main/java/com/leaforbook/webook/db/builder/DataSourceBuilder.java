package com.leaforbook.webook.db.builder;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;

import com.leaforbook.webook.db.constant.DBCPConstants;
import com.leaforbook.webook.db.exception.LoadDbConfigDataException;
import com.leaforbook.webook.db.exception.LoadDbConfigFileException;
import com.leaforbook.webook.util.PropertiesReader;
import com.leaforbook.webook.util.exception.LoadPropertiesFileException;

/**
 * 数据源构造器
 * @author xiaoyilin
 *
 */
public class DataSourceBuilder {
	
	/**
	 * 根据配置文件路径，获取数据源。如果加载过了，就使用内存中的数据源，如果没有加载过，就构造新的数据源。
	 * @param configPath
	 * @return
	 * @throws LoadDbConfigDataException 
	 * @throws LoadDbConfigFileException 
	 */
	public static DataSource getDataSource(String configPath) throws LoadDbConfigFileException, LoadDbConfigDataException {
		DataSource dataSource = DataSourceContainer.getInstance().getMap().get(configPath);
		if(dataSource==null) {
			synchronized (DataSourceContainer.getInstance()) {
				dataSource = DataSourceContainer.getInstance().getMap().get(configPath);
				if(dataSource==null) {
					dataSource = loadProperties(configPath);
					DataSourceContainer.getInstance().getMap().put(configPath, dataSource);
				}
			}
		}
		return dataSource;
	}
	
	
	/**
	 * 使用properties工具把配置信息加载进来，用于构造新的DataSource。
	 * @param configPath
	 * @return
	 * @throws LoadDbConfigFileException 
	 * @throws LoadDbConfigDataException 
	 */
	private static DataSource loadProperties(String configPath) throws LoadDbConfigFileException, LoadDbConfigDataException {
		PropertiesReader reader=null;
		try {
			reader = new PropertiesReader(configPath);
		} catch (LoadPropertiesFileException e) {
			throw new LoadDbConfigFileException(configPath);
		}
		
		BasicDataSource bds = new BasicDataSource();
		
		String driverName = reader.getString(DBCPConstants.DRIVERCLASSNAME);
		String url = reader.getString(DBCPConstants.URL);
		String username = reader.getString(DBCPConstants.USERNAME);
		String password = reader.getString(DBCPConstants.PASSWORD);
		
		if(StringUtils.isEmpty(driverName)||StringUtils.isEmpty(url)||StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
			throw new LoadDbConfigDataException(configPath);
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

/**
 * 用于存储数据源，避免数据源重复生成
 * @author xiaoyilin
 *
 */
class DataSourceContainer {
	
	private volatile static DataSourceContainer container = new DataSourceContainer();
	
	private volatile Map<String,DataSource> map = new HashMap<String,DataSource>();
	
	public Map<String, DataSource> getMap() {
		return map;
	}

	private DataSourceContainer() {}
	
	public static DataSourceContainer getInstance() {
		return container;
	}
	
}
