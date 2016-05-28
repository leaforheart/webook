package com.leaforbook.webook.db.builder;

import javax.sql.DataSource;

import com.leaforbook.webook.db.exception.DataSourceCreateException;
import com.leaforbook.webook.util.PropertiesReader;
import com.leaforbook.webook.util.exception.LoadPropertiesFileException;

public abstract class DataSourceFactory {
	protected String dataSourceName;
	protected String dataSourceNameConfigPath;
	
	public DataSourceFactory(String dataSourceName,String dataSourceNameConfigPath) {
		this.dataSourceName = dataSourceName;
		this.dataSourceNameConfigPath = dataSourceNameConfigPath;
	}
	
	public DataSource getDataSource() throws DataSourceCreateException {
		DataSource dataSource = DataSourceContainer.getInstance().getMap().get(dataSourceName);
		if(dataSource!=null) {
			return dataSource;
		}
		try {
			PropertiesReader dsReader = new PropertiesReader(dataSourceNameConfigPath);
			String dbPath = dsReader.getString(dataSourceName);
			PropertiesReader dbReader = new PropertiesReader(dbPath);
			dataSource = loadProperties(dbReader);
			DataSourceContainer.getInstance().getMap().put(dataSourceName, dataSource);
		} catch (LoadPropertiesFileException e) {
			throw new DataSourceCreateException(dataSourceName,dataSourceNameConfigPath);
		}
		return dataSource;
	}
	
	protected abstract DataSource loadProperties(PropertiesReader reader) throws DataSourceCreateException;
}
