package com.leaforbook.webook.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.alibaba.fastjson.JSON;
import com.leaforbook.webook.db.bean.CountBean;
import com.leaforbook.webook.db.builder.QueryRunnerBuilder;
import com.leaforbook.webook.db.exception.WdbException;
import com.leaforbook.webook.db.util.PostgreSQLToCount;
import com.leaforbook.webook.util.ListToArray;

public abstract class BasicSwapper implements Swapper {
	
	private static QueryRunner queryRuner = QueryRunnerBuilder.getInstance().getQueryRunner();
	
	private ConnectionPool pool;
	
	public ConnectionPool getPool() {
		return pool;
	}
	
	@Override
	public void setPool(ConnectionPool pool) {
		this.pool = pool;
	}

	protected QueryRunner getQueryRuner() {
		return queryRuner;
	}

	@Override
	public int[] dmlBatch(String sql, List<List<Object>> params) throws WdbException {
		int[] result = null;
		try {
			Connection conn = pool.getConnection();
			Object[][] arrParams = ListToArray.twoDimensional(params);
			result = queryRuner.batch(conn, sql, arrParams);
		} catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(params),e);
		} 
		return result;
	}

	@Override
	public int dml(String sql) throws WdbException {
		Connection conn = pool.getConnection();
		int result = 0;
		try {
			result = queryRuner.update(conn, sql);
		} catch (SQLException e) {
			throw new WdbException(sql,e);
		}
		return result;
	}

	@Override
	public int dml(String sql, List<Object> params) throws WdbException {
		Connection conn = pool.getConnection();
		Object[] arrParams = ListToArray.oneDimensional(params);
		int result = 0;
		try {
			result = queryRuner.update(conn, sql,arrParams);
		} catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(params),e);
		}
		return result;
	}

	@Override
	public <T> List<T> insertBatch(String sql, Class<T> T, List<List<Object>> params) throws WdbException {
		Connection conn = pool.getConnection();
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		Object[][] arrParams = ListToArray.twoDimensional(params);
		try {
			result = queryRuner.insertBatch(conn, sql, rsh, arrParams);
		} catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(params),e);
		}
		return result;
	}

	@Override
	public <T> T insert(String sql, Class<T> T) throws WdbException {
		Connection conn = pool.getConnection();
		ResultSetHandler<T> rsh = new BeanHandler<T>(T);
		T result = null;
		try {
			result = queryRuner.insert(conn, sql, rsh);
		} catch (SQLException e) {
			throw new WdbException(sql,e);
		}
		return result;
	}

	@Override
	public <T> T insert(String sql, Class<T> T, List<Object> params) throws WdbException {
		Connection conn = pool.getConnection();
		ResultSetHandler<T> rsh = new BeanHandler<T>(T);
		T result = null;
		Object[] arrParams = ListToArray.oneDimensional(params);
		try {
			result = queryRuner.insert(conn, sql, rsh, arrParams);
		} catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(params),e);
		}
		return result;
	}

	@Override
	public <T> List<T> query(String sql, Class<T> T) throws WdbException {
		Connection conn = pool.getConnection();
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		try {
			result = queryRuner.query(conn, sql, rsh);
		} catch (SQLException e) {
			throw new WdbException(sql,e);
		}
		return result;
	}

	@Override
	public <T> List<T> query(String sql, Class<T> T, List<Object> params) throws WdbException {
		Connection conn = pool.getConnection();
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		Object[] arrParams = ListToArray.oneDimensional(params);
		try {
			result = queryRuner.query(conn, sql, rsh,arrParams);
		} catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(params),e);
		}
		return result;
	}

	@Override
	public <T> T queryUnique(String sql, Class<T> T) throws WdbException {
		Connection conn = pool.getConnection();
		ResultSetHandler<T> rsh = new BeanHandler<T>(T);
		T result = null;
		try {
			result = queryRuner.query(conn, sql, rsh);
		} catch (SQLException e) {
			throw new WdbException(sql,e);
		}
		return result;
	}

	@Override
	public <T> T queryUnique(String sql, Class<T> T, List<Object> params) throws WdbException {
		Connection conn = pool.getConnection();
		ResultSetHandler<T> rsh = new BeanHandler<T>(T);
		T result = null;
		Object[] arrParams = ListToArray.oneDimensional(params);
		try {
			result = queryRuner.query(conn, sql, rsh,arrParams);
		} catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(params),e);
		}
		return result;
	}

	@Override
	public long queryCount(String sql) throws WdbException {
		Connection conn = this.getPool().getConnection();
		ResultSetHandler<CountBean> rsh = new BeanHandler<CountBean>(CountBean.class);
		CountBean result = null;
		long count = 0;
		try {
			sql = PostgreSQLToCount.convert(sql);
			result = this.getQueryRuner().query(conn, sql, rsh);
		} catch (SQLException e) {
			throw new WdbException(sql,e);
		}
		if(result!=null) {
			count = result.getCount();
		}
		return count;
	}

	@Override
	public long queryCount(String sql, List<Object> params) throws WdbException {
		Connection conn = this.getPool().getConnection();
		ResultSetHandler<CountBean> rsh = new BeanHandler<CountBean>(CountBean.class);
		CountBean result = null;
		long count = 0;
		try {
			sql = PostgreSQLToCount.convert(sql);
			Object[] arrParams = ListToArray.oneDimensional(params);
			result = this.getQueryRuner().query(conn, sql, rsh,arrParams);
		}catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(params),e);
		}
		if(result!=null) {
			count = result.getCount();
		}
		return count;
	}

	@Override
	public abstract <T> List<T> queryPage(String sql, long limit, long offset, Class<T> T) throws WdbException;

	@Override
	public abstract <T> List<T> queryPage(String sql, long limit, long offset,Class<T> T, List<Object> params) throws WdbException;

}
