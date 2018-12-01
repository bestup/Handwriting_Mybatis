package com.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.mapper.UserMapper;
import com.mybatis.pojo.User;

/**
 * @author: tanglong
 * @Description: 使用mybatis框架   连接数据库，进行查询 user信息
 */
public class MybatisDemo {
	
	
	public static void main(String[] args) {
		
		MybatisDemo md = new MybatisDemo();
		
		//两种查询方式结果一样
		//md.listBaseMapper();
		md.listBasePojo();
	}
	
	
	/**
	 * @MethodName listBaseMapper
	 * @Description:  mybatis查询 基于mapper接口的实现
	 * @Param 
	 * @Return void
	 */
	public void listBaseMapper() {
		
		try {
			String resource = "mybatis-config.xml";
			
			//加载配置文件到IO中
			InputStream inputStream = Resources.getResourceAsStream(resource);
			
			//根据配置信息使用流对象创建 会话工厂
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
			
			//创建sqlsession  , session就是程序员与数据库交互的入口 ,sqlsession 对外提供数据库查询服务
			SqlSession sqlSession = ssf.openSession();
			
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			List<User> users = userMapper.findAll();
			
			if(users.size()>0) {
				for(User user:users) {
					System.out.println(user);
				}
			}
			sqlSession.commit();
			
			//关闭连接
			sqlSession.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public void listBasePojo() {
		try {
			String resource = "mybatis-config.xml";
			
			//加载配置文件到IO中
			InputStream inputStream = Resources.getResourceAsStream(resource);
			
			//根据配置信息使用流对象创建 会话工厂
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
			
			//创建sqlsession  , session就是程序员与数据库交互的入口 ,sqlsession 对外提供数据库查询服务
			SqlSession sqlSession = ssf.openSession();
			
			List<User> users = sqlSession.selectList("com.mybatis.mapper.UserMapper.findAll");
			
			if(users.size()>0) {
				for(User user:users) {
					System.out.println(user);
				}
			}
			sqlSession.commit();
			
			//关闭连接
			sqlSession.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	
}
