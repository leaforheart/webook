package com.leaforbook.webook.db.builder;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

public class DataSourceContainer {
	
	private static DataSourceContainer container = new DataSourceContainer();
	
	private Map<String,DataSource> map = new HashMap<String,DataSource>();
	
	public Map<String, DataSource> getMap() {
		return map;
	}

	private DataSourceContainer() {}
	
	public static DataSourceContainer getInstance() {
		if(container==null) {
			container = new DataSourceContainer();
		}
		return container;
	}
	
}