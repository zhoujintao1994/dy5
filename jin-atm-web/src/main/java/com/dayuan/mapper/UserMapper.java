package com.dayuan.mapper;

import org.apache.ibatis.annotations.Param;

import com.dayuan.domain.User;

public interface UserMapper {
	
	int addUser(User user);
	
	User getUser(String username);
	
	int modifyPwd(@Param("username")String username,@Param("password")String password);
	
	User getUserById(Integer userId);
}
