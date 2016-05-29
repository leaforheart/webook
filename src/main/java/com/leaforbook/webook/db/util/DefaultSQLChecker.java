package com.leaforbook.webook.db.util;

import com.leaforbook.webook.db.constant.SQLConstants;
import com.leaforbook.webook.db.exception.SQLCheckException;

public class DefaultSQLChecker implements SQLChecker {

	@Override
	public String check(String sql) throws SQLCheckException {
		sql = sql.replace(SQLConstants.SPLIT_SYMBOL, SQLConstants.JOINT_SYMBOL);
		return sql;
	}

}
