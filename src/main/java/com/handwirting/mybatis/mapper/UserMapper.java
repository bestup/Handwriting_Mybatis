package com.handwirting.mybatis.mapper;

import java.util.List;

import com.handwirting.mybatis.pojo.User;

/**
 * @author: tanglong
 * @Description:
 */
public interface UserMapper {
	List<User> findAll();
}
