package com.handwirting.mybatis.test;

import java.util.List;

import com.handwirting.mybatis.mapper.UserMapper;
import com.handwirting.mybatis.pojo.User;
import com.handwirting.mybatis.session.SqlSession;
import com.handwirting.mybatis.session.SqlSessionFacotry;

/**
 * @author: tanglong
 * @Description:
 */
public class TestMybatis {
	public static void main(String[] args) {
		
		//1、实例化SqlSessionFactory,加载数据库配置文件以及mapper.xml文件到configuration对象
		SqlSessionFacotry factory = new SqlSessionFacotry();
		
		//2、获取sqlsession对象
		SqlSession session = factory.openSession();
		//System.out.println(session);	//可以成功输出 com.handwirting.mybatis.session.DefaultSqlSession@21b8d17c
		
		//3、通过动态代理跨越面向接口编程和ibatis编程模型的鸿沟
		UserMapper userMapper = session.getMapper(UserMapper.class);
		
		//4、遵循jdbc规范，通过底层的三大对象的合作完成数据查询和数据转化
		List<User> users = userMapper.findAll();
		
		for(User user: users) {
			System.out.println(user);
		}	
	}
}
