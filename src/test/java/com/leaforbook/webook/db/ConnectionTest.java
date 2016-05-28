package com.leaforbook.webook.db;

import com.leaforbook.webook.db.builder.BasicConnectionBuilder;
import com.leaforbook.webook.db.builder.ConnectionBuilder;
import com.leaforbook.webook.db.exception.ConnectionBuilderException;

public class ConnectionTest {

	public static void main(String[] args) throws ConnectionBuilderException {
		ConnectionBuilder builder = new BasicConnectionBuilder();
		System.out.println(builder.getConnection());
		System.out.println(builder.getConnection());
		System.out.println(builder.getConnection());
		System.out.println(builder.getConnection());
		System.out.println(builder.getConnection());
		System.out.println(builder.getConnection());
		System.out.println(builder.getConnection());
		System.out.println(builder.getConnection());
	}

}
