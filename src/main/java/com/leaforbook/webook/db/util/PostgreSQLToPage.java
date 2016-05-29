package com.leaforbook.webook.db.util;

import com.leaforbook.webook.db.exception.SQLToPageException;

public class PostgreSQLToPage {
	public static String convert(String sql) throws SQLToPageException {
		sql = sql.trim();
		int select = sql.indexOf("select");
		int from = sql.indexOf("from");
		if(select!=0||from<10) {
			throw new SQLToPageException(sql);
		}
		if(sql.lastIndexOf(";")==sql.length()-1) {
			sql = sql.substring(0, sql.length()-2);
		}
		sql += " limit ? offset ?";
		return sql;
	}
}
