package com.dayuan.serviceimpl;

import com.dayuan.domain.User;

public interface UserService {
	
	User regist(String username, String passWord);
	
	User login(String username, String passWord);
	
	User modifyPwd(String oldPassword, String newPassword, String comfirmPassword, String username);
	
	User getUserId(Integer userId);

}
