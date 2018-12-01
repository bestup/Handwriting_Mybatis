package com.handwirting.mybatis.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.handwirting.mybatis.pojo.User;

/**
 * @author: tanglong
 * @Description:	反射工具类
 */
public class ReflectionUtil {
	
	/**
	 * @MethodName setPropToBean
	 * @Description:  	为指定的bean的propName属性赋值为value
	 * @param bean		目标对象
	 * @param propName	对象的属性名
	 * @param value		值
	 */
	public static void setPropToBean(Object bean,String propName,Object value) {
		Field field = null;	
		try {
			//获得对象指定属性
			field = bean.getClass().getDeclaredField(propName);
			
			//将对象的私有属性设置为可通过反射进行访问
			field.setAccessible(true);
			
			//为属性赋值
			field.set(bean, value);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * @MethodName 	setPropToBeanFromResultSet
	 * @Description:  	从resultSet中读取一行数据，并填充到指定的实体bean
	 * @param entity	得到填充的实体bean
	 * @param rs 		从数据库加载的数据
	 * @throws SQLException 
	 */
	public static void setPropToBeanFromResultSet(Object entity,ResultSet rs) throws SQLException {
		
		//通过反射获取对象的所有字段
		Field[] declaredFields = entity.getClass().getDeclaredFields();
		
		//遍历所有的字段，从resultSet中读取响应的数据，并填充至对象的属性中
		for(int i = 0;i < declaredFields.length;i++) {
			if(declaredFields[i].getType().getSimpleName().equals("String")) {
				//如果是字符串类型的数据
				setPropToBean(entity,declaredFields[i].getName(),rs.getString(declaredFields[i].getName()));
			}else if(declaredFields[i].getType().getSimpleName().equals("Integer")) {
				//如果是int类型的数据
				setPropToBean(entity,declaredFields[i].getName(),rs.getInt(declaredFields[i].getName()));
			}else if(declaredFields[i].getType().getSimpleName().equals("Long")) {
				//如果是Long类型的数据
				setPropToBean(entity,declaredFields[i].getName(),rs.getLong(declaredFields[i].getName()));
			}
		}	
	}
	
	//测试setPropToBean方法    ，通过
	/*public static void main(String[] args) {
		
		User user = new User();
		setPropToBean(user, "age", 12);
		System.out.println(user);
	}*/
	
	
	
	
}
