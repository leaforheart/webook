package com.leaforbook.webook.db.util;

public class PostgreSQLToPage {
	public static String convert(String sql) {
		sql = "select * from (" + sql + ") sub" + " limit ? offset ?";
		return sql;
	}
}
