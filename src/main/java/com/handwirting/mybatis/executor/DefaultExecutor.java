package com.handwirting.mybatis.executor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.handwirting.mybatis.config.Configuration;
import com.handwirting.mybatis.config.MapperStatement;
import com.handwirting.mybatis.util.ReflectionUtil;

/**
 * @author: tanglong
 * @Description:
 */
public class DefaultExecutor implements Executor{

	private Configuration config;
	
	public DefaultExecutor(Configuration config) {
		super();
		this.config = config;
	}
	
	@Override
	public <E> List<E> query(MapperStatement ms, Object parameter) {
		
		//定义集合返回结果集
		List<E> resultList = new ArrayList<E>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			//加载驱动
			Class clazz = Class.forName(config.getJdbcDriver());
			
			//获取连接，从MapperStatement对象中获取数据库信息
			conn = DriverManager.getConnection(config.getJdbcUrl(), config.getJdbcUserName(), config.getJdbcPassword());
			
			//创建perparstatement,从MapperStatement对象中获取sql语句
			pstm = conn.prepareStatement(ms.getSql());
			
			//处理sql语句中的占位符
			parameterize(pstm,parameter);
			
			//执行查询，返回结果集给ResultSet 
			rs = pstm.executeQuery();
			
			//将结果集使用反射技术填充 到list中
			handlerResultSet(rs,resultList,ms.getResultType());
			
		} catch (Exception e) {
			System.out.println("数据库查询出错");
			e.printStackTrace();
		}finally {
			//关闭连接
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultList;
	}
	
	//读取resultset中的数据，并转换成目标对象
	private <E> void handlerResultSet(ResultSet resultSet,List<E> ret,String className) {
		Class<E> clazz = null;
		try {
			//通过反射获取类对象
			clazz = (Class<E>) Class.forName(className);	
			
			while(resultSet.next()) {
				//通过反射实例化对象
				Object entity = clazz.newInstance();
				
				//使用反射工具将resultSet中的数据填充到entity中
				ReflectionUtil.setPropToBeanFromResultSet(entity,resultSet);
				
				//将对象加入返回集合中
				ret.add((E) entity);		
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//对preparstatement中的占位符进行处理
	private void parameterize(PreparedStatement preparedStatement,Object parameter) throws Exception {
		if(parameter instanceof Integer) {
			preparedStatement.setInt(1, (int)parameter);
		}else if(parameter instanceof Long) {
			preparedStatement.setLong(1, (Long)parameter);
		}else if(parameter instanceof String) {
			preparedStatement.setString(1, (String)parameter);
		}
	}
	
	

}
