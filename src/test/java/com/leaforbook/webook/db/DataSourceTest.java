package com.leaforbook.webook.db;

import com.leaforbook.webook.db.builder.BasicDataSourceBuilder;
import com.leaforbook.webook.db.builder.DataSourceContainer;
import com.leaforbook.webook.db.builder.DataSourceBuilder;
import com.leaforbook.webook.db.constant.DataSourceConstants;
import com.leaforbook.webook.db.exception.DataSourceCreateException;

public class DataSourceTest {
	public static void main(String[] args) {
		DataSourceBuilder factory = new BasicDataSourceBuilder(DataSourceConstants.DEFAULT,"dataSource.properties");
		try {
			System.out.println(factory.getDataSource());
			System.out.println(DataSourceContainer.getInstance().getMap().get(DataSourceConstants.DEFAULT));
			System.out.println(factory.getDataSource());
			factory = new BasicDataSourceBuilder(DataSourceConstants.DEFAULT,"dataSource.properties");
			System.out.println(factory.getDataSource());
		} catch (DataSourceCreateException e) {
			e.printStackTrace();
		}
	}
}
