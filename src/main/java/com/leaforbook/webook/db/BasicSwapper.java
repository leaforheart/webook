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
import com.leaforbook.webook.db.util.SQLToCount;
import com.leaforbook.webook.util.ListToArray;

/**
 * Swapper接口的基础实现类，是一个抽象类，实现了所有数据库都通用的操作。
 * @author xiaoyilin
 *
 */
public abstract class BasicSwapper implements Swapper {
	
	private static QueryRunner queryRuner = QueryRunnerBuilder.getInstance().getQueryRunner();
	
	private ConnectionPool pool;
	
	/**
	 * 获取连接池
	 * @return
	 */
	public ConnectionPool getPool() {
		return pool;
	}
	
	/**
	 * 设置连接池
	 * @param pool
	 */
	@Override
	public void setPool(ConnectionPool pool) {
		this.pool = pool;
	}
	
	protected QueryRunner getQueryRuner() {
		return queryRuner;
	}
	
	/**
	 * 批量增删改，返回被操作的行数
	 * @param sql
	 * @param params
	 * @return
	 * @throws WdbException
	 */
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
	
	/**
	 * 增删改，返回被操作的行数，无参数
	 * @param sql
	 * @return
	 * @throws WdbException
	 */
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
	
	/**
	 * 增删改，返回被操作的行数，带参数
	 * @param sql
	 * @param params
	 * @return
	 * @throws WdbException
	 */
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
	
	/**
	 * 批量插入，返回被插入的数据。可以用来返回自增长id。
	 * @param sql
	 * @param T
	 * @param params
	 * @return
	 * @throws WdbException
	 */
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
	
	/**
	 * 插入操作，返回被插入的数据，无参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
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
	
	/**
	 * 插入操作，返回被插入的数据，带参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
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
	
	/**
	 * 查询操作，返回查询到的数据，无参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
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
	
	/**
	 * 查询操作，返回查询到的数据，带参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
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

	/**
	 * 查询单条数据，如果存在多条，返回第一条，如果不存在，返回null，无参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
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

	/**
	 * 查询单条数据，如果存在多条，返回第一条，如果不存在，返回null，带参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
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

	/**
	 * 查询改sql能查询出多少条数据，通常用于分页查询，无参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	@Override
	public long queryCount(String sql) throws WdbException {
		Connection conn = this.getPool().getConnection();
		ResultSetHandler<CountBean> rsh = new BeanHandler<CountBean>(CountBean.class);
		CountBean result = null;
		long count = 0;
		try {
			sql = SQLToCount.convert(sql);
			result = this.getQueryRuner().query(conn, sql, rsh);
		} catch (SQLException e) {
			throw new WdbException(sql,e);
		}
		if(result!=null) {
			count = result.getCount();
		}
		return count;
	}

	/**
	 * 查询改sql能查询出多少条数据，通常用于分页查询，带参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	@Override
	public long queryCount(String sql, List<Object> params) throws WdbException {
		Connection conn = this.getPool().getConnection();
		ResultSetHandler<CountBean> rsh = new BeanHandler<CountBean>(CountBean.class);
		CountBean result = null;
		long count = 0;
		try {
			sql = SQLToCount.convert(sql);
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

	/**
	 * 分页查询，无参数
	 * @param sql
	 * @param limit 一页多少条数据
	 * @param page 第几页
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	@Override
	public abstract <T> List<T> queryPage(String sql, long limit, long page, Class<T> T) throws WdbException;

	/**
	 * 分页查询，带参数
	 * @param sql
	 * @param limit 一页多少条数据
	 * @param page 第几页
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	@Override
	public abstract <T> List<T> queryPage(String sql, long limit, long page,Class<T> T, List<Object> params) throws WdbException;

}
