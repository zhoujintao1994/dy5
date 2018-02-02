package com.dayuan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuan.domain.User;
import com.dayuan.dto.AjaxDTO;
import com.dayuan.exception.BizException;


@Controller
public  class BaseController {
	@RequestMapping("/toUserCenter")
	public String toUserCenter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "usercenter";
	}
//	@RequestMapping("/toPage")
//	public String toPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		return req.getParameter("pageName");
//	}
	
	@ExceptionHandler(BizException.class)
	@ResponseBody
	public AjaxDTO handlerBizException(BizException be) {
		return AjaxDTO.faild(be.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public AjaxDTO handlerException(Exception e) {
		e.printStackTrace();
		return AjaxDTO.faild("操作失败，请联系客服");
	}
	
	
	protected String getUserName(HttpSession session) {
		Object obj = session.getAttribute("user");
		if (null == obj) {
			throw new BizException("用户不存在");
		}
		
		User user = (User)obj;
		
		return user.getUsername();
	}
	
	protected int getUserId(HttpSession session) {
		Object obj = session.getAttribute("user");
		if (null == obj) {
			throw new BizException("用户不存在");
		}
		
		User user = (User)obj;
		return user.getId();
	}
	
}
