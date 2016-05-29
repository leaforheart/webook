package com.leaforbook.webook.db.util;

import com.leaforbook.webook.db.exception.SQLCheckException;

public interface SQLChecker {
	String check(String sql) throws SQLCheckException;
}
