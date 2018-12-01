package com.handwirting.mybatis.config;

/**
 * @author: tanglong
 * @Description: MapperStatement 存 mapper.xml 的信息
 */
public class MapperStatement {
	
	//mapper.xml中的namespace
	private String namespace;
	
	//mapper.xml中sql语句的 id
	private String sourceId;
	
	//mapper.xml中sql语句的 返回类型
	private String resultType;
	
	//mapper.xml中sql语句
	private String sql;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
	
	
}
