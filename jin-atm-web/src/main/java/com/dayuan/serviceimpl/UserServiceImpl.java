package com.dayuan.serviceimpl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuan.domain.User;
import com.dayuan.exception.BizException;

import com.dayuan.mapper.UserMapper;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User regist(String username, String passWord) {

		User selcetUser = userMapper.getUser(username);
		if (null != selcetUser) {
			throw new BizException("该用户已经存在");
		}
		User user = new User();

		if (StringUtils.isBlank(username)) {
			throw new BizException("账号不能为空");
		}

		if (StringUtils.isBlank(passWord)) {
			throw new BizException("密码不能为空");
		}
		user.setPassword(DigestUtils.md5Hex(passWord + username));
		user.setUsername(username);

		int rows = userMapper.addUser(user);

		if (rows != 1) {
			throw new BizException("注册失败");
		}

		return user;
	}

	@Override
	public User login(String username, String passWord) {

		User user = userMapper.getUser(username);
		if (null == user) {
			throw new BizException("用户名不存在");
		}

		if (StringUtils.isBlank(username)) {
			throw new BizException("账号不能为空");
		}

		if (StringUtils.isBlank(passWord)) {
			throw new BizException("账号不能为空");
		}

		if (!user.getPassword().equals(DigestUtils.md5Hex(passWord + username))) {
			throw new BizException("密码错误");
		}

		return user;
	}

	@Override
	public User modifyPwd(String oldPassword, String newPassword, String comfirmPassword, String username) {
		// TODO Auto-generated method stub
		User user = userMapper.getUser(username);
		System.out.println(user);

		if (null == user) {
			throw new BizException("用户名不存在");
		}
		// //����̲�
		// if (null == newPassword || "".equals(newPassword)) {
		// throw new BizException("密码不能为空");
		// }
		if (StringUtils.isBlank(newPassword)) {
			throw new BizException("新密码不能为空");
		}

		if (StringUtils.isBlank(oldPassword)) {
			throw new BizException("旧密码不能为空");
		}

		if (!newPassword.equals(comfirmPassword)) {
			throw new BizException("两次密码不相等");
		}

		if (oldPassword.equals(newPassword)) {
			throw new BizException("新密码和老密码不能相同");
		}

		if (!user.getPassword().equals(DigestUtils.md5Hex(oldPassword + username))) {
			throw new BizException("老密码错误");
		}

		newPassword = DigestUtils.md5Hex(newPassword + username);
		// comfirmPassword = DigestUtils.md5Hex(newPassword + username);
		int rows = userMapper.modifyPwd(username, newPassword);

		if (1 != rows) {
			throw new BizException("修改失败");
		}
		return user;
	}

	@Override
	public User getUserId(Integer userId) {
		User user = userMapper.getUserById(userId);
		return user;
	}

}
