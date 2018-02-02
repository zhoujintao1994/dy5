package com.dayuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dayuan.domain.User;
import com.dayuan.domain.WxLogin;
import com.dayuan.dto.AjaxDTO;
import com.dayuan.dto.WXAuthenDTO;
import com.dayuan.dto.WXUserInfoDTO;
import com.dayuan.exception.BizException;
import com.dayuan.handler.WXAuthenHandler;
import com.dayuan.serviceimpl.ThirdAuthorService;
import com.dayuan.serviceimpl.UserService;
import com.dayuan.utill.AESUtils;

@Controller
@RequestMapping("/wxAuthen")
public class WXAuthenController {

//	private String appId = "2018010817121159519";
//	private String redirectUri = "http://127.0.0.1:8080/wxAuthen/wxLoginCallBack.do";
																				
	private static final Logger log = LoggerFactory.getLogger(WXAuthenHandler.class);

	@Autowired
	private ThirdAuthorService thirdAuthorService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private WXAuthenHandler wXAuthenHandler;

	@RequestMapping("/toWxLogin")
	public String toWxLogin() {// 微信登录 获得二维码登录接口
		
//		// 获取code请求地址
//		StringBuilder.append("http://payhub.dayuanit.com/weixin/connect/qrconnect.do?").append("appid=").append(appId)
//				.append("&").append("redirect_uri=").append(redirectUri).append("&").append("response_type=code")
//				.append("&").append("scope=snsapi_login");
		String codeUrl = wXAuthenHandler.getCodeUrl();
		return "redirect:" + codeUrl;
	}

	@RequestMapping("/wxLoginCallBack")
	public String wxLoginCallBack(String code, HttpServletRequest request, HttpSession session) {
//		System.out.println(">>>>>>>code:" + code);
//
//		StringBuilder StringBuilder = new StringBuilder();
//								//获取access_token的请求地址
//		StringBuilder.append("http://payhub.dayuanit.com/weixin/sns/oauth2/access_token.do?").append("appid=")
//				.append(appId).append("&").append("redirect_uri=").append(redirectUri).append("&").append("code=")
//				.append(code).append("&").append("authorization_code");
//
//		CloseableHttpClient httpclient = HttpClients.createDefault();// 创建http客户端
//		HttpGet httpGet = new HttpGet(StringBuilder.toString());// 获得get请求的url地址

//		try {
//			CloseableHttpResponse httpResponse = httpclient.execute(httpGet);//执行其中的请求
//			String result = EntityUtils.toString(httpResponse.getEntity());//获取响应头里的信息转化为字符串的结果集
//			System.out.println(">>>>>>>>>result:" + result);
//			
//			WXAuthenDTO authenDTO = JSON.parseObject(result, WXAuthenDTO.class);//将字符串转化为对象，便于操作
			WXAuthenDTO authenDTO = wXAuthenHandler.getAccessToken(code);
			
			log.info(">>>>>>>>>accessToken:" + authenDTO.getAccess_token());
			
			WxLogin wxLogin = thirdAuthorService.saveAuthor(authenDTO);//保存返回的参数到数据库
			
			
			if (null != wxLogin.getUserId()) {
				
				User user = userService.getUserId(wxLogin.getUserId());
				if (null == user) {
					throw new BizException("认证失败");
				}
				session.setAttribute("user", user);
				return "redirect:/user/toUserCenter.do";
			}
			
//			HttpGet userInfoGet = new HttpGet("http://payhub.dayuanit.com/weixin/sns/userinfo.do?access_token="
//					+ authenDTO.getAccess_token() + "&openid=" + authenDTO.getOpenid());
//			CloseableHttpClient httpclient = HttpClients.createDefault();// 创建http客户端
//			CloseableHttpResponse httpResponse;
//			httpResponse = httpclient.execute(userInfoGet);
//			String result = EntityUtils.toString(httpResponse.getEntity());
//			log.info(">>>>>>>>>用户微信信息:" + result);
//
//			Map<String, String> wxInfo = JSON.parseObject(result, Map.class);
//			log.info(">>>>>>>>>>>nickname=" + wxInfo.get("nickname"));
			WXUserInfoDTO wxInfo = wXAuthenHandler.getWxUserInfo(authenDTO);
			
			session.setAttribute("nickname", wxInfo.getNickname());
			session.setAttribute("headimgurl", wxInfo.getHeadimgurl());
			session.setAttribute("openid",wxInfo.getOpenid());//通过0penId来绑定

		
		return "redirect:/wxAuthen/toBindLogin.do";
	}
	
	@RequestMapping("/toBindLogin")
	public String toBindLogin() {
		
		return "bindLogin";	
	}
	
	@RequestMapping("/wxLogin")
	@ResponseBody
	public AjaxDTO wxLogin(String username, String passWord, String openid, HttpSession session) {
		User user = thirdAuthorService.bindUser(username, passWord,  (String)session.getAttribute("openid") );
		
		session.setAttribute("user", user);
		return AjaxDTO.success();
	}
}
