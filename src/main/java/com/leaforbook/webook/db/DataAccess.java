package com.leaforbook.webook.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.leaforbook.webook.db.builder.QueryRunnerBuilder;
import com.leaforbook.webook.db.constant.SQLConstants;
import com.leaforbook.webook.db.exception.DataAccessException;
import com.leaforbook.webook.db.exception.SQLCheckException;
import com.leaforbook.webook.db.util.DefaultSQLChecker;
import com.leaforbook.webook.db.util.SQLChecker;
import com.leaforbook.webook.util.ListToArray;

public class DataAccess {
	
	private QueryRunner queryRuner = QueryRunnerBuilder.getInstance().getQueryRunner();
	private SQLChecker sqlChecker;

	protected QueryRunner getQueryRuner() {
		return queryRuner;
	}
	
	public DataAccess() {
		this(new DefaultSQLChecker());
	}
	
	public DataAccess(SQLChecker sqlChecker) {
		this.sqlChecker = sqlChecker;
	}

	public int[] iudBatch(Connection conn, String sql, List<List<Object>> params) throws DataAccessException {
		
		int[] result = null;
		try {
			String finalSql = sqlChecker.check(sql);
			Object[][] arrParams = ListToArray.twoDimensional(params);
			result = queryRuner.batch(conn, finalSql, arrParams);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_UIDBATCH,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_UIDBATCH,sql);
		}
		return result;
		
	}
	
}
