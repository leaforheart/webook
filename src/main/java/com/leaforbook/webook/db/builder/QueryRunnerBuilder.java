package com.leaforbook.webook.db.builder;

import org.apache.commons.dbutils.QueryRunner;

/**
 * QueryRunner构造器，保证整个系统只有一个QueryRunner
 * @author xiaoyilin
 *
 */
public class QueryRunnerBuilder {
	private static QueryRunnerBuilder builder = new QueryRunnerBuilder();
	private static QueryRunner queryRunner = new QueryRunner();
	
	private QueryRunnerBuilder(){}
	
	public static QueryRunnerBuilder getInstance() {
		return builder;
	}
	
	public QueryRunner getQueryRunner() {
		return queryRunner;
	}
}
