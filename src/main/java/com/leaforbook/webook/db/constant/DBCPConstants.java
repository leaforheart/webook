package com.leaforbook.webook.db.constant;

public interface DBCPConstants {
	String DRIVERCLASSNAME = "jdbc.driverClassName";
	String URL = "jdbc.url";
	String USERNAME = "jdbc.username";
	String PASSWORD = "jdbc.password";
	
	String INITTIALSIZE = "pool.initialSize";
	String MAXTOTAL = "pool.maxTotal";
	String MAXIDLE = "pool.maxIdle";
	String MINIDLE = "pool.minIdle";
	String MAXWAITMILLI = "pool.maxWaitMillis";
	
	String DEFAULTAUTOCOMMIT = "transaction.defaultAutoCommit";
	
	String POOLPREPAREDSTATEMENTS = "prepared.poolPreparedStatements";
	String MAXOPENPREPAREDSTATEMENTS = "prepared.maxOpenPreparedStatements";
}
