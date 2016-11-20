package com.leaforbook.webook.db.util;

public class MySQLToPage {
	public static String convert(String sql) {
		sql = "select * from (" + sql + ") sub" + " limit ?,?";
		return sql;
	}
}
