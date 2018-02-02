package com.dayuan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuan.domain.PageHolder;
import com.dayuan.dto.AjaxDTO;
import com.dayuan.dto.MessageDTO;
import com.dayuan.mapper.MessageMapper;
import com.dayuan.serviceimpl.MessageService;
import com.dayuan.serviceimpl.UserService;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

	@Autowired
	private MessageService messageService;

	private Logger log = LoggerFactory.getLogger(BankCardController.class);

	@RequestMapping("/messageInfo")
	@ResponseBody
	public AjaxDTO messageInfo(HttpSession session) {
		MessageDTO msg = messageService.setMessage(getUserId(session));
		return AjaxDTO.success(msg);
		// return AjaxDTO.success(messageService.setMessage(getUserId(session)));
	}

	// @RequestMapping("/modifymessage")
	// @ResponseBody
	// public AjaxDTO modifymessage(HttpSession session) {
	//
	// return AjaxDTO.success(messageService.modifyMessage(getUserId(session)));
	//
	// }

	@RequestMapping("/messageFlow")
	@ResponseBody
	public AjaxDTO messageFlow(int currentPageNum , HttpSession session) {
		PageHolder msg = messageService.listMessage(currentPageNum ,getUserId(session));
//		messagemapper.updateStatus(1, getUserId(session));
		return AjaxDTO.success(msg);
	}
	
	@RequestMapping("/toMessage")
	public String toMessage(HttpSession session) {
		messageService.readMessage(getUserId(session));
		return "messageFlow";
	}
}