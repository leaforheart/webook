package com.leaforbook.webook.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.alibaba.fastjson.JSON;
import com.leaforbook.webook.db.exception.WdbException;
import com.leaforbook.webook.db.util.PostgreSQLToPage;
import com.leaforbook.webook.util.ListToArray;

public class PGSwapper extends BasicSwapper implements Swapper {
	
	public PGSwapper() {
		
	}
	
	public PGSwapper(ConnectionPool pool) {
		this.setPool(pool);
	}

	@Override
	public <T> List<T> queryPage(String sql, long limit, long offset, Class<T> T) throws WdbException {
		Connection conn = this.getPool().getConnection();
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		Object[] arrParams = new Object[2];
		try {
			sql = PostgreSQLToPage.convert(sql);
			
			arrParams[0] = limit;
			arrParams[1] = offset;
			result = this.getQueryRuner().query(conn, sql, rsh,arrParams);
		}catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(arrParams),e);
		} 
		return result;
	}

	@Override
	public <T> List<T> queryPage(String sql, long limit, long offset, Class<T> T, List<Object> params)  throws WdbException{
		Connection conn = this.getPool().getConnection();
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		List<Object> pageParams = new ArrayList<Object>();
		try {
			sql = PostgreSQLToPage.convert(sql);
			pageParams.addAll(params);
			pageParams.add(limit);
			pageParams.add(offset);
			Object[] arrParams = ListToArray.oneDimensional(pageParams);
			result = this.getQueryRuner().query(conn, sql, rsh,arrParams);
		}catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(pageParams),e);
		} 
		return result;
	}

}
