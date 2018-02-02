package com.dayuan.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuan.domain.User;
import com.dayuan.domain.WxLogin;
import com.dayuan.dto.WXAuthenDTO;
import com.dayuan.exception.BizException;
import com.dayuan.mapper.WxLoginMapper;
import com.dayuan.utill.AESUtils;

@Service
public class ThirdAuthorServiceImpl implements ThirdAuthorService {

	@Autowired
	private WxLoginMapper wxLoginMapper;

	@Autowired
	private UserService userService;

	@Override
	public WxLogin saveAuthor(WXAuthenDTO authenDTO) {
		WxLogin wxLogin = wxLoginMapper.getAuthor(authenDTO.getOpenid());
		
		if (null == wxLogin) {
			wxLogin = new WxLogin();
			wxLogin.setExpiresIn(authenDTO.getExpires_in());
			wxLogin.setOpenid(authenDTO.getOpenid());
			wxLogin.setRefreshToken(authenDTO.getRefresh_token());
			wxLogin.setAcceccToken(authenDTO.getAccess_token());
			wxLogin.setUserId(null);
			
			int rows = wxLoginMapper.add(wxLogin);

			if (1 != rows) {
				throw new BizException("登录失败");
			}
		} else {
			wxLogin.setAcceccToken(authenDTO.getAccess_token());
			wxLogin.setExpiresIn(authenDTO.getExpires_in());
			wxLogin.setOpenid(authenDTO.getOpenid());
			wxLogin.setRefreshToken(authenDTO.getRefresh_token());
			int rows = wxLoginMapper.update(wxLogin);

			if (1 != rows) {
				throw new BizException("登录失败");
			}
		}

		return wxLogin;
	}

	@Override
	public WxLogin queryAuthor(String openid) {
		// TODO Auto-generated method stub
		WxLogin wxLogin = wxLoginMapper.getAuthor(openid);
		return wxLogin;
	}

	@Override
	public User bindUser(String username, String passWord, String openid) {
		
//		try {
//			 openid =AESUtils.decodeMsg(openid.getBytes());
//			 System.out.println("<<<<<<<<解密后："+openid);
//		}catch (Exception e) {
//			throw new BizException("揭秘失败");		}
		WxLogin wxLogin = wxLoginMapper.getAuthor(openid);
		if (null == wxLogin) {
			throw new BizException("认证失败");
		}

		User user = userService.login(username, passWord);

		wxLogin.setUserId(user.getId());

		int rows = wxLoginMapper.update(wxLogin);

		if (1 != rows) {
			throw new BizException("认证失败");
		}
		return user;
	}

}
