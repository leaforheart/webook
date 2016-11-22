package com.leaforbook.webook.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.alibaba.fastjson.JSON;
import com.leaforbook.webook.db.exception.WdbException;
import com.leaforbook.webook.db.util.PostgreSQLToPage;
import com.leaforbook.webook.util.ListToArray;

/**
 * 针对PostgreSQL，对Swapper接口的特定实现。
 * @author xiaoyilin
 *
 */
public class PostgreSQLSwapper extends BasicSwapper implements Swapper {
	
	/**
	 * 无参构造器
	 */
	public PostgreSQLSwapper() {
		
	}
	
	/**
	 * 设置连接池的构造器
	 * @param pool
	 */
	public PostgreSQLSwapper(ConnectionPool pool) {
		this.setPool(pool);
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
	public <T> List<T> queryPage(String sql, long limit, long page, Class<T> T) throws WdbException {
		if(!Pattern.matches("^[1-9]\\d*$", String.valueOf(limit))||!Pattern.matches("^[1-9]\\d*$",String.valueOf(page))) {
			throw new RuntimeException("limit or page not positive integer! limit="+limit+",page="+page);
		}
		
		Connection conn = this.getPool().getConnection();
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		Object[] arrParams = new Object[2];
		try {
			sql = PostgreSQLToPage.convert(sql);
			arrParams[0] = limit;
			arrParams[1] = (page-1)*limit;
			result = this.getQueryRuner().query(conn, sql, rsh, arrParams);
		}catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(arrParams),e);
		} 
		return result;
	}

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
	public <T> List<T> queryPage(String sql, long limit, long page, Class<T> T, List<Object> params)  throws WdbException{
		if(!Pattern.matches("^[1-9]\\d*$", String.valueOf(limit))||!Pattern.matches("^[1-9]\\d*$",String.valueOf(page))) {
			throw new RuntimeException("limit or page not positive integer! limit="+limit+",page="+page);
		}
		
		Connection conn = this.getPool().getConnection();
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		List<Object> pageParams = new ArrayList<Object>();
		try {
			sql = PostgreSQLToPage.convert(sql);
			pageParams.addAll(params);
			pageParams.add(limit);
			pageParams.add((page-1)*limit);
			Object[] arrParams = ListToArray.oneDimensional(pageParams);
			result = this.getQueryRuner().query(conn, sql, rsh,arrParams);
		}catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(pageParams),e);
		} 
		return result;
	}

}
