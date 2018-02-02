package com.dayuan.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuan.domain.User;
import com.dayuan.dto.AjaxDTO;
import com.dayuan.exception.BizException;
import com.dayuan.serviceimpl.UserService;

@Controller
@RequestMapping("/userInfo")

public class UserInfoController extends BaseController {

	@Autowired
	private UserService userService;

	static Logger log = LoggerFactory.getLogger(UserInfoController.class);
	// public void setUserService(UserService userService) {
	// this.userService = userService;
	// }

	@RequestMapping("/toPage")
	public String toPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String pageName = req.getParameter("pageName");
		return req.getParameter("pageName");
	}
	
	@RequestMapping("/toModifyPwd")
	public String toModifyPwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "modifyPwd";
	}
	
	@RequestMapping("/modifyPwd")
	@ResponseBody
	public AjaxDTO modifyPwd(String oldPassword, String newPassword, String comfirmPassword, HttpSession session){
		System.out.println(">>>>>  UserInfoController.modifyPwd()  >>>>>>>");
//		String username = req.getParameter("username");
//		String passWord = req.getParameter("passWord");
//		OutputStream os = resp.getOutputStream();
		
		try {
			userService.modifyPwd(oldPassword, newPassword, comfirmPassword, getUserName(session));
			session.invalidate();
		} catch (BizException be) {
			return AjaxDTO.faild(be.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			return AjaxDTO.faild("操作失败，请联系客服");
		}
		return AjaxDTO.success();
	}
}