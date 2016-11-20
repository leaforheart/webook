package com.leaforbook.webook.db.util;

public class SQLToCount {
	public static String convert(String sql) {
		sql = "select count(1) count from (" + sql + ") sub";
		return sql;
	}
}
