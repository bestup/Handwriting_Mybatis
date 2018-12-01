package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.pojo.User;

/**
 * @author: tanglong
 * @Description: jdbc连接数据的实现，在看简易版mybatis时对比 ，便于理解
 * 
 */
public class JdbcDemo {
	
	//1、配置数据的连接信息
	public static String url = "jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8";
	public static String username = "root";
	public static String password = "0000";
	
	public static void main(String[] args)  {
		
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			//加载驱动
			Class clazz = Class.forName("com.mysql.jdbc.Driver");
			
			//床架数据库连接，一个conn代表一个数据连接
			conn = DriverManager.getConnection(url, username, password);
			
			//定义sql语句
			String sql = "select * from user";
			pstm = conn.prepareStatement(sql);
			
			//执行查询，返回结果集给ResultSet 
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setAddress(rs.getString("address"));
				
				System.out.println(user.toString());	
			}
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
	}
	
	
	
	
	
	
	
	
	
	
	

}
