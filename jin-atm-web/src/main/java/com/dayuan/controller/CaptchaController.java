package com.dayuan.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dayuan.captcha.Captcha;
import com.dayuan.captcha.GifCaptcha;


@Controller
public class CaptchaController {
	
	@RequestMapping("/getCaptcha")
	public void getCaptcha(HttpServletResponse response, HttpSession session) {
		try {//配置
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");

			// gif格式动画验证码 宽，高，位数。
			Captcha captcha = new GifCaptcha(146, 33, 6);
		
			//服务器返回一个输出流
			captcha.out(response.getOutputStream());
											//验证码以字符的格式并且小写 设置一个session	
			session.setAttribute("capcode", captcha.text().toLowerCase());
		} catch (Exception e) {
		}
	}

}
