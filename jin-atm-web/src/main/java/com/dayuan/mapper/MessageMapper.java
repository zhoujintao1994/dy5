package com.dayuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuan.domain.Message;

public interface MessageMapper {
	
	int countMessage(Integer userId);
	
	List<Message> selectMessage(Integer userId);
	
	int add(Message message);
	
	int updateStatus(@Param("status")Integer status, @Param("userId")Integer userId);
	
	List<Message> listMessage(@Param("userId")Integer userId, @Param("offset")Integer offset,  @Param("prePage")Integer prePage);
	
	int countAllMessage(Integer userId);
}
