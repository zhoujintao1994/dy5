package com.dayuan.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dayuan.domain.User;

public interface bankCardMapping2 {
	  @Select("SELECT * FROM users WHERE id = #{userId}")
	  User getUser(@Param("userId") String userId);
	} 
