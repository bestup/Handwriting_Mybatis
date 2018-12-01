package com.handwirting.mybatis.session;

import java.lang.reflect.Proxy;
import java.util.List;

import com.handwirting.mybatis.build.MappedProxy;
import com.handwirting.mybatis.config.Configuration;
import com.handwirting.mybatis.config.MapperStatement;
import com.handwirting.mybatis.executor.DefaultExecutor;
import com.handwirting.mybatis.executor.Executor;

/**
 * @author: tanglong
 * @Description:
 */
public class DefaultSqlSession implements SqlSession{
	
	private Configuration config;
	
	private Executor executor;

	public DefaultSqlSession(Configuration config) {
		this.config = config;
		this.executor = new DefaultExecutor(config);
	}

	@Override
	public <T> T selectOne(String statement, Object parameter) {
		List<T> selectList = this.selectList(statement, parameter);
		if(selectList == null || selectList.size() == 0) {
			return null;
		}
		if( selectList.size() == 1) {
			return selectList.get(0);
		}
		throw new RuntimeException("too many results");
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter) {
		MapperStatement msp = config.getMapperStatements().get(statement);
		return executor.query(msp, parameter);
	}

	@Override
	public <T> T getMapper(Class<T> type) {
		MappedProxy mp = new MappedProxy(this);
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[] {type}, mp);
	}

}
