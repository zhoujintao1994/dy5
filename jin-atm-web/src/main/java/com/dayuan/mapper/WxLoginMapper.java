package com.dayuan.mapper;

import org.apache.ibatis.annotations.Param;

import com.dayuan.domain.WxLogin;

public interface WxLoginMapper {
	int add(WxLogin author);
	
	WxLogin getAuthor(String openid);
	
	int update(WxLogin wxLogin);
}
