package com.leaforbook.webook.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.leaforbook.webook.db.bean.CountBean;
import com.leaforbook.webook.db.builder.BasicConnectionBuilder;
import com.leaforbook.webook.db.builder.ConnectionBuilder;
import com.leaforbook.webook.db.exception.ConnectionBuilderException;
import com.leaforbook.webook.db.exception.DataAccessException;

public class DataAccessTest {

	public static void main(String[] args) throws DataAccessException, SQLException, ConnectionBuilderException {
		DataAccess access = new PostgreSQLDataAccess();
		ConnectionBuilder builder = new BasicConnectionBuilder();
		Connection conn = builder.getConnection();
		String sql="select * from test where name like ?";
		List<Object> params = new ArrayList<Object>();
		params.add("%leaforbook%");
		long i = access.queryCount(conn, sql, CountBean.class,params);
		System.out.println(i);
		List<TestBean> list = access.queryPage(conn, sql, 3, 0, TestBean.class,params);
		System.out.println(list);
		

		//conn.commit();
		//conn.close();

	}

}
