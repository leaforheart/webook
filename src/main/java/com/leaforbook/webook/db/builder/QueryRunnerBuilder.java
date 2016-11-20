package com.leaforbook.webook.db.builder;

import org.apache.commons.dbutils.QueryRunner;

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
