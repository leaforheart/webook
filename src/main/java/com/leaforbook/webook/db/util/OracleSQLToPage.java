package com.leaforbook.webook.db.util;

/**
 * Oracle把普通查询SQL转化成分页查询SQL
 * @author xiaoyilin
 *
 */
public class OracleSQLToPage {
	
	/**
	 * 把普通查询SQL转化成分页查询SQL
	 * @param sql
	 * @return
	 */
	public static String convert(String sql) {
		sql = "select outer2.* from (select outer1.*,rownum  rn from (" + sql + ") outer1 where rownum<=?) outer2 where rn>=?";
		return sql;
	}
}
