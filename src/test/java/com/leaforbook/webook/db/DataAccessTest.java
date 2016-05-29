package com.leaforbook.webook.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.leaforbook.webook.db.builder.BasicConnectionBuilder;
import com.leaforbook.webook.db.builder.ConnectionBuilder;
import com.leaforbook.webook.db.exception.ConnectionBuilderException;
import com.leaforbook.webook.db.exception.DataAccessException;

public class DataAccessTest {

	public static void main(String[] args) throws DataAccessException, SQLException, ConnectionBuilderException {
//		DataAccess access = new DataAccess();
//		ConnectionBuilder builder = new BasicConnectionBuilder();
//		Connection conn = builder.getConnection();
//		String sql="insert into test(id,name) values (?,?)";
//		List<Object> list1 = new ArrayList<Object>();
//		list1.add(20);
//		list1.add("leaforbook20");
//		List<Object> list2 = new ArrayList<Object>();
//		list2.add(17);
//		list2.add("leaforbook17");
//		List<Object> list3 = new ArrayList<Object>();
//		list3.add(18);
//		list3.add("leaforbook18");
//		List<Object> list4 = new ArrayList<Object>();
//		list4.add(19);
//		list4.add("leaforbook19");
//		List<List<Object>> params = new ArrayList<List<Object>>();
//		params.add(list1);
//		params.add(list2);
//		params.add(list3);
//		params.add(list4);
//		access.dmlBatch(conn, sql, params);
		//conn.commit();
		//conn.close();

	}

}
