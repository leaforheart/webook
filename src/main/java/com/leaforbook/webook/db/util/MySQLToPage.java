package com.leaforbook.webook.db.util;

/**
 * Mysql把普通查询SQL转化成分页查询SQL
 * @author xiaoyilin
 *
 */
public class MySQLToPage {
	
	/**
	 * 把普通查询SQL转化成分页查询SQL
	 * @param sql
	 * @return
	 */
	public static String convert(String sql) {
		sql = "select * from (" + sql + ") sub" + " limit ?,?";
		return sql;
	}
}
