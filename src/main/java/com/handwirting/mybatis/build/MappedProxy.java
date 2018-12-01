package com.handwirting.mybatis.build;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

import com.handwirting.mybatis.session.SqlSession;

/**
 * @author: tanglong
 * @Description:
 */
public class MappedProxy implements InvocationHandler{

	private SqlSession session;
	
	public MappedProxy(SqlSession session) {
		this.session = session;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class returnType =  method.getReturnType();
		
		//判断方法的返回类型是不是集合
		if(Collection.class.isAssignableFrom(returnType)) {
			return session.selectList(method.getDeclaringClass().getName()+"."+method.getName(), args==null?null:args[0]);
		}
		return session.selectList(method.getDeclaringClass().getName()+"."+method.getName(), args==null?null:args[0]);
	}

}
