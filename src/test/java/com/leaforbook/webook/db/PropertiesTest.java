package com.leaforbook.webook.db;

import com.leaforbook.webook.db.constant.DBCPConstants;
import com.leaforbook.webook.util.PropertiesReader;
import com.leaforbook.webook.util.exception.CastPropertiesValueException;
import com.leaforbook.webook.util.exception.LoadPropertiesFileException;

public class PropertiesTest {
	public static void main(String[] args) {
		try {
			PropertiesReader reader = new PropertiesReader("dataSource.properties");
			System.out.print(reader.getInt(DBCPConstants.INITTIALSIZE));
		} catch (LoadPropertiesFileException e) {
			e.printStackTrace();
		} catch (CastPropertiesValueException e) {
			e.printStackTrace();
		}
		
	}
}
