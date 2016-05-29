package com.leaforbook.webook.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.leaforbook.webook.db.bean.CountBean;
import com.leaforbook.webook.db.constant.SQLConstants;
import com.leaforbook.webook.db.exception.DataAccessException;
import com.leaforbook.webook.db.exception.SQLCheckException;
import com.leaforbook.webook.db.exception.SQLToCountException;
import com.leaforbook.webook.db.exception.SQLToPageException;
import com.leaforbook.webook.db.util.SQLChecker;
import com.leaforbook.webook.db.util.PostgreSQLToCount;
import com.leaforbook.webook.db.util.PostgreSQLToPage;
import com.leaforbook.webook.util.ListToArray;

public class PostgreSQLDataAccess extends DataAccess {
	
	public PostgreSQLDataAccess() {
		super();
	}
	
	public PostgreSQLDataAccess(SQLChecker sqlChecker) {
		super(sqlChecker);
	}

	@Override
	public int queryCount(Connection conn, String sql, Class<CountBean> count) throws DataAccessException {
		ResultSetHandler<CountBean> rsh = new BeanHandler<CountBean>(count);
		CountBean result = null;
		int coun = 0;
		try {
			sql = this.getSqlChecker().check(sql);
			sql = PostgreSQLToCount.convert(sql);
			result = this.getQueryRuner().query(conn, sql, rsh);
			if(result!=null) {
				coun = result.getCount();
			}
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYCOUNT,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYCOUNT);
		} catch (SQLToCountException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYPAGE,sql,true);
		}
		return coun;
	}

	@Override
	public int queryCount(Connection conn, String sql, Class<CountBean> count,List<Object> params) throws DataAccessException {
		ResultSetHandler<CountBean> rsh = new BeanHandler<CountBean>(count);
		CountBean result = null;
		int coun = 0;
		try {
			sql = this.getSqlChecker().check(sql);
			sql = PostgreSQLToCount.convert(sql);
			Object[] arrParams = ListToArray.oneDimensional(params);
			result = this.getQueryRuner().query(conn, sql, rsh,arrParams);
			if(result!=null) {
				coun = result.getCount();
			}
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYCOUNT,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYCOUNT);
		} catch (SQLToCountException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYPAGE,sql,true);
		}
		return coun;
	}

	@Override
	public <T> List<T> queryPage(Connection conn, String sql,long limit,long offset, Class<T> T) throws DataAccessException {
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		try {
			String finalSql = this.getSqlChecker().check(sql);
			finalSql = PostgreSQLToPage.convert(finalSql);
			Object[] arrParams = new Object[2];
			arrParams[0] = limit;
			arrParams[1] = offset;
			result = this.getQueryRuner().query(conn, finalSql, rsh,arrParams);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYPAGE,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYPAGE);
		} catch (SQLToPageException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYPAGE,sql,true);
		}
		return result;
	}

	@Override
	public <T> List<T> queryPage(Connection conn, String sql,long limit,long offset, Class<T> T,List<Object> params) throws DataAccessException {
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		try {
			String finalSql = this.getSqlChecker().check(sql);
			finalSql = PostgreSQLToPage.convert(finalSql);
			List<Object> pageParams = new ArrayList<Object>();
			pageParams.addAll(params);
			pageParams.add(limit);
			pageParams.add(offset);
			Object[] arrParams = ListToArray.oneDimensional(pageParams);
			result = this.getQueryRuner().query(conn, finalSql, rsh,arrParams);
		} catch (SQLCheckException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYPAGE,sql);
		} catch (SQLException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYPAGE);
		} catch (SQLToPageException e) {
			throw new DataAccessException(SQLConstants.METHOD_QUERYPAGE,sql,true);
		}
		return result;
	}

}
