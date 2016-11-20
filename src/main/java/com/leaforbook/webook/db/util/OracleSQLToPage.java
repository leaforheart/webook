package com.leaforbook.webook.db.util;

public class OracleSQLToPage {
	public static String convert(String sql) {
		sql = "select outer2.* from (select outer1.*,rownum  rn from (" + sql + ") outer1 where rownum<=?) outer2 where rn>=?";
		return sql;
	}
}
