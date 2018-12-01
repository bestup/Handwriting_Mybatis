package com.mybatis.mapper;

import java.util.List;

import com.mybatis.pojo.User;

/**
 * @author: tanglong
 * @Description:
 */
public interface UserMapper {
	List<User> findAll();
}
