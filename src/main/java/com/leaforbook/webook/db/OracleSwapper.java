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
import com.leaforbook.webook.db.util.OracleSQLToPage;

public class OracleSwapper extends BasicSwapper implements Swapper {
	
	public OracleSwapper() {
		
	}
	
	public OracleSwapper(ConnectionPool pool) {
		this.setPool(pool);
	}

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
			sql = OracleSQLToPage.convert(sql);
			arrParams[0] = limit*page;
			arrParams[1] = limit*(page-1)+1;
			result = this.getQueryRuner().query(conn, sql, rsh, arrParams);
		}catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(arrParams),e);
		} 
		return result;
	}

	@Override
	public <T> List<T> queryPage(String sql, long limit, long page, Class<T> T, List<Object> params) throws WdbException {
		if(!Pattern.matches("^[1-9]\\d*$", String.valueOf(limit))||!Pattern.matches("^[1-9]\\d*$",String.valueOf(page))) {
			throw new RuntimeException("limit or page not positive integer! limit="+limit+",page="+page);
		}
		
		Connection conn = this.getPool().getConnection();
		ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(T);
		List<T> result = null;
		List<Object> pageParams = new ArrayList<Object>();
		try {
			sql = OracleSQLToPage.convert(sql);
			pageParams.addAll(params);
			pageParams.add(limit*page);
			pageParams.add(limit*(page-1)+1);
			result = this.getQueryRuner().query(conn, sql, rsh, pageParams);
		}catch (SQLException e) {
			throw new WdbException(sql+",Parameters:"+JSON.toJSONString(pageParams),e);
		} 
		return result;
	}

}
