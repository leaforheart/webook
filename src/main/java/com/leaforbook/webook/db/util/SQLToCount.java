package com.leaforbook.webook.db.util;

/**
 * 把普通查询SQL转化成查询该SQL可以命中多少条数据的SQL
 * @author xiaoyilin
 *
 */
public class SQLToCount {
	
	/**
	 * 把普通查询SQL转化成查询该SQL可以命中多少条数据的SQL
	 * @param sql
	 * @return
	 */
	public static String convert(String sql) {
		sql = "select count(1) count from (" + sql + ") sub";
		return sql;
	}
}
