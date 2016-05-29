package com.leaforbook.webook.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.leaforbook.webook.db.bean.CountBean;
import com.leaforbook.webook.db.builder.QueryRunnerBuilder;
import com.leaforbook.webook.db.constant.SQLConstants;
import com.leaforbook.webook.db.exception.DataAccessException;
import com.leaforbook.webook.db.exception.SQLCheckException;
import com.leaforbook.webook.db.util.DefaultSQLChecker;
import com.leaforbook.webook.db.util.SQLChecker;
import com.leaforbook.webook.util.ListToArray;

public abstract class DataAccess {
	
	private QueryRunner queryRuner = QueryRunnerBuilder.getInstance().getQueryRunner();
	private SQLChecker sqlChecker;

	protected SQLChecker getSqlChecker() {
		return sqlChecker;
	}

	protected QueryRunner getQueryRuner() {
		return queryRuner;
	}
	
	public DataAccess() {
		this(new DefaultSQLChecker());
	}
	
	public DataAccess(SQLChecker sqlChecker) {
		this.sqlChecker = sqlChecker;
	}

	public int[] dmlBatch(Connection conn, String sql, List<List<Object>> params) throws DataAccessException {
		
		int[] result = null;
		try {
			String finalSql = sqlChecker.check(sql);
			Object[][] arrParams = ListToArray.twoDimensional(params);
			result = queryRuner.batch(conn, finalSql, arrParams);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_DMLBATCH,sql);
		} catch (SQLException e) {
			e.printStackTrace();
			e.getNextException().getNextException().printStackTrace();
			throw new DataAccessException(SQLConstants.METHOD_DMLBATCH);
		}
		return result;
		
	}
	
	public int dml(Connection conn, String sql) throws DataAccessException {
		int result = 0;
		try {
			String finalSql = sqlChecker.check(sql);
			result = queryRuner.update(conn, finalSql);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_DML,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_DML);
		}
		return result;
	}
	
	public int dml(Connection conn, String sql,List<Object> params) throws DataAccessException {
		int result = 0;
		try {
			String finalSql = sqlChecker.check(sql);
			Object[] arrParams = ListToArray.oneDimensional(params);
			result = queryRuner.update(conn, finalSql,arrParams);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_DML,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_DML);
		}
		return result;
	}
	
	public <T> List<T> insertBatch(Connection conn, String sql , Class<T> T, List<List<Object>> params) throws DataAccessException {
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		try {
			String finalSql = sqlChecker.check(sql);
			Object[][] arrParams = ListToArray.twoDimensional(params);
			result = queryRuner.insertBatch(conn, finalSql, rsh, arrParams);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_INSERTBATCH,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_INSERTBATCH);
		}
		return result;
	}
	
	public <T> T insert(Connection conn, String sql , Class<T> T) throws DataAccessException {
		ResultSetHandler<T> rsh = new BeanHandler<T>(T);
		T result = null;
		try {
			String finalSql = sqlChecker.check(sql);
			result = queryRuner.insert(conn, finalSql, rsh);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_INSERT,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_INSERT);
		}
		return result;
	}
	
	public <T> T insert(Connection conn, String sql , Class<T> T, List<Object> params) throws DataAccessException {
		ResultSetHandler<T> rsh = new BeanHandler<T>(T);
		T result = null;
		try {
			String finalSql = sqlChecker.check(sql);
			Object[] arrParams = ListToArray.oneDimensional(params);
			result = queryRuner.insert(conn, finalSql, rsh, arrParams);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_INSERT,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_INSERT);
		}
		return result;
	}
	
	public <T> List<T> query(Connection conn, String sql, Class<T> T) throws DataAccessException {
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		try {
			String finalSql = sqlChecker.check(sql);
			result = queryRuner.query(conn, finalSql, rsh);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERY,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERY);
		}
		return result;
	}
	
	public <T> List<T> query(Connection conn, String sql, Class<T> T, List<Object> params) throws DataAccessException {
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		try {
			String finalSql = sqlChecker.check(sql);
			Object[] arrParams = ListToArray.oneDimensional(params);
			result = queryRuner.query(conn, finalSql, rsh,arrParams);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERY,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERY);
		}
		return result;
	}
	
	public <T> T queryUnique(Connection conn, String sql, Class<T> T) throws DataAccessException {
		ResultSetHandler<T> rsh = new BeanHandler<T>(T);
		T result = null;
		try {
			String finalSql = sqlChecker.check(sql);
			result = queryRuner.query(conn, finalSql, rsh);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYUNIQUE,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYUNIQUE);
		}
		return result;
	}
	
	public <T> T queryUnique(Connection conn, String sql, Class<T> T, List<Object> params) throws DataAccessException {
		ResultSetHandler<T> rsh = new BeanHandler<T>(T);
		T result = null;
		try {
			String finalSql = sqlChecker.check(sql);
			Object[] arrParams = ListToArray.oneDimensional(params);
			result = queryRuner.query(conn, finalSql, rsh,arrParams);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYUNIQUE,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYUNIQUE);
		}
		return result;
	}
	
	public abstract int queryCount(Connection conn, String sql, Class<CountBean> count) throws DataAccessException;
	
	public abstract int queryCount(Connection conn, String sql, Class<CountBean> count, List<Object> params) throws DataAccessException;
	
	public abstract <T> List<T> queryPage(Connection conn, String sql,long limit,long offset,Class<T> T) throws DataAccessException;
	
	public abstract <T> List<T> queryPage(Connection conn, String sql,long limit,long offset,Class<T> T, List<Object> params) throws DataAccessException;
	
}
