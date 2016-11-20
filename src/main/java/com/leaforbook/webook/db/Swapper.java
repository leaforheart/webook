package com.leaforbook.webook.db;

import java.util.List;

import com.leaforbook.webook.db.exception.WdbException;

public interface Swapper {
	int[] dmlBatch(String sql, List<List<Object>> params) throws WdbException;
	int dml(String sql) throws WdbException;
	int dml(String sql,List<Object> params) throws WdbException;
	<T> List<T> insertBatch(String sql , Class<T> T, List<List<Object>> params) throws WdbException;
	<T> T insert(String sql , Class<T> T) throws WdbException;
	<T> T insert(String sql , Class<T> T, List<Object> params) throws WdbException;
	<T> List<T> query(String sql, Class<T> T) throws WdbException;
	<T> List<T> query(String sql, Class<T> T, List<Object> params) throws WdbException;
	<T> T queryUnique(String sql, Class<T> T) throws WdbException;
	<T> T queryUnique(String sql, Class<T> T, List<Object> params) throws WdbException;
	long queryCount(String sql) throws WdbException;
	long queryCount(String sql,List<Object> params) throws WdbException;
	<T> List<T> queryPage(String sql,long limit,long page,Class<T> T) throws WdbException;
	<T> List<T> queryPage(String sql,long limit,long page,Class<T> T, List<Object> params) throws WdbException;
	
	void setPool(ConnectionPool pool);
}