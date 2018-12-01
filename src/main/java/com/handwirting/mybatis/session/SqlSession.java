package com.handwirting.mybatis.session;

import java.util.List;

/**
 * @author: tanglong
 * @Description:
 * 
 * sqlSession是 mybatis暴露被外部的接口，实现CRUD的能力
 * 
 * 1、对外提供数据访问的API
 * 2、把外部的请求经过参数处理之后转发给Executor执行
 */
public interface SqlSession {
	
	/**
	 * @MethodName selectOne
	 * @Description: 		根据传入的条件查询单一结果
	 * @param statement 	方法对应的sql语句，namespace + id
	 * @param parameter		要传入到sql语句中的查询参数
	 */
	<T> T selectOne(String statement,Object parameter);
	
	/**
	 * @MethodName selectList
	 * @Description: 	根据条件查询果，返回泛型集合
	 * @param statement	方法对应的sql语句，namespace + id
	 * @param parameter	要传入到sql语句中的查询参数
	 */
	<E> List<E> selectList(String statement,Object parameter) ;
	
	/**
	 * @MethodName getMapepr
	 * @Description: 根据mapper接口获取接口对应的动态代理实现
	 * @@param type
	 */
	<T> T getMapper(Class<T> type);
}
