package com.leaforbook.webook.db.util;

import com.leaforbook.webook.db.exception.SQLToCountException;

public class PostgreSQLToCount {
	public static String convert(String sql) throws SQLToCountException {
		sql = sql.trim();
		int select = sql.indexOf("select");
		int from = sql.indexOf("from");
		if(select!=0||from<9) {
			throw new SQLToCountException(sql);
		}
		sql = sql.substring(0, 6) + " count(1) count " + sql.substring(from);
		return sql;
	}
}
