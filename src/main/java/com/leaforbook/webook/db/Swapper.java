package com.leaforbook.webook.db;

import java.util.List;

import com.leaforbook.webook.db.exception.WdbException;

/**
 * 应用程序和数据库进行交换数据的接口。
 * @author xiaoyilin
 *
 */
public interface Swapper {
	
	/**
	 * 批量增删改，返回被操作的行数
	 * @param sql
	 * @param params
	 * @return
	 * @throws WdbException
	 */
	int[] dmlBatch(String sql, List<List<Object>> params) throws WdbException;
	
	/**
	 * 增删改，返回被操作的行数，无参数
	 * @param sql
	 * @return
	 * @throws WdbException
	 */
	int dml(String sql) throws WdbException;
	
	/**
	 * 增删改，返回被操作的行数，带参数
	 * @param sql
	 * @param params
	 * @return
	 * @throws WdbException
	 */
	int dml(String sql,List<Object> params) throws WdbException;
	
	/**
	 * 批量插入，返回被插入的数据。可以用来返回自增长id。
	 * @param sql
	 * @param T
	 * @param params
	 * @return
	 * @throws WdbException
	 */
	<T> List<T> insertBatch(String sql , Class<T> T, List<List<Object>> params) throws WdbException;
	
	/**
	 * 插入操作，返回被插入的数据，无参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	<T> T insert(String sql , Class<T> T) throws WdbException;
	
	/**
	 * 插入操作，返回被插入的数据，带参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	<T> T insert(String sql , Class<T> T, List<Object> params) throws WdbException;
	
	/**
	 * 查询操作，返回查询到的数据，无参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	<T> List<T> query(String sql, Class<T> T) throws WdbException;
	
	/**
	 * 查询操作，返回查询到的数据，带参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	<T> List<T> query(String sql, Class<T> T, List<Object> params) throws WdbException;
	
	/**
	 * 查询单条数据，如果存在多条，返回第一条，如果不存在，返回null，无参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	<T> T queryUnique(String sql, Class<T> T) throws WdbException;
	
	/**
	 * 查询单条数据，如果存在多条，返回第一条，如果不存在，返回null，带参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	<T> T queryUnique(String sql, Class<T> T, List<Object> params) throws WdbException;
	
	/**
	 * 查询改sql能查询出多少条数据，通常用于分页查询，无参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	long queryCount(String sql) throws WdbException;
	
	/**
	 * 查询改sql能查询出多少条数据，通常用于分页查询，带参数
	 * @param sql
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	long queryCount(String sql,List<Object> params) throws WdbException;
	
	/**
	 * 分页查询，无参数
	 * @param sql
	 * @param limit 一页多少条数据
	 * @param page 第几页
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	<T> List<T> queryPage(String sql,long limit,long page,Class<T> T) throws WdbException;
	
	/**
	 * 分页查询，带参数
	 * @param sql
	 * @param limit 一页多少条数据
	 * @param page 第几页
	 * @param T
	 * @return
	 * @throws WdbException
	 */
	<T> List<T> queryPage(String sql,long limit,long page,Class<T> T, List<Object> params) throws WdbException;
	
	/**
	 * 设置连接池
	 * @param pool
	 */
	void setPool(ConnectionPool pool);
}