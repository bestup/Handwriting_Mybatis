package com.handwirting.mybatis.executor;

import java.util.List;

import com.handwirting.mybatis.config.MapperStatement;

/**
 * @author: tanglong
 * @Description:
 * Executor是mybatis核心接口之一，定义了数据库操作的基本方法，SqlSession的功能都是基于它来实现的
 */
public interface Executor {
	
	
	/**
	 * @MethodName query
	 * @Description:  	查询接口
	 * @param ms		封装了sql语句的MapperStatement对象
	 * @param parameter	传入sql的参数	
	 * @Return List<E>	将数据转换成指定对象的结果集返回
	 */
	<E> List<E> query(MapperStatement ms,Object parameter);
	
}
