package com.dayuan.serviceimpl;

import com.dayuan.domain.User;
import com.dayuan.domain.WxLogin;
import com.dayuan.dto.WXAuthenDTO;

public interface ThirdAuthorService {

	WxLogin saveAuthor(WXAuthenDTO authenDTO);
	
	WxLogin queryAuthor(String openid);

	User bindUser(String username, String passWord, String openid);
}
