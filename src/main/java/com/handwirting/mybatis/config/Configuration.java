package com.handwirting.mybatis.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: tanglong 
 * @Description: 定义配置类存配置信息，连接数据库
 */
public class Configuration {
	
	private String jdbcDriver;
	private String jdbcUrl;
	private String jdbcUserName;
	private String jdbcPassword;
	
	Map<String,MapperStatement> mapperStatements = new HashMap<String,MapperStatement>();

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUserName() {
		return jdbcUserName;
	}

	public void setJdbcUserName(String jdbcUserName) {
		this.jdbcUserName = jdbcUserName;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	public Map<String, MapperStatement> getMapperStatements() {
		return mapperStatements;
	}

	public void setMapperStatements(Map<String, MapperStatement> mapperStatements) {
		this.mapperStatements = mapperStatements;
	}
	
	
	

}
