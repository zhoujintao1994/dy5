package com.dayuan.handler;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.dayuan.dto.WXAuthenDTO;
import com.dayuan.dto.WXUserInfoDTO;
import com.dayuan.exception.BizException;
import com.dayuan.utill.ApiConnector;


@Component
public class WXAuthenHandler {
	private static final Logger log = LoggerFactory.getLogger(WXAuthenHandler.class);
	
	@Value("${wx_appId}")
	private String appId;
	
	@Value("${wx_redirectUrl}")
	private String redirectUri;
	
	@Value("${wx_secret}")
	private String secret;
	
	@Value("${wx_codeUrl}")
	private String codeUrl;
	
	@Value("${wx_accessTokenUrl}")
	private String accessTokenUrl;
	
	@Value("${wx_wxUserInfoUrl}")
	private String wxUserInfoUrl;
	

	public String getCodeUrl() {
		StringBuilder StringBuilder = new StringBuilder();
		// 获取code请求地址
		StringBuilder.append(codeUrl).append("?").append("appid=").append(appId)
				.append("&").append("redirect_uri=").append(redirectUri).append("&").append("response_type=code")
				.append("&").append("scope=snsapi_login");
		return StringBuilder.toString();
	}

	public WXAuthenDTO getAccessToken(String code) {

		log.info(">>>>>>>code:" + code);

		StringBuilder StringBuilder = new StringBuilder();
		// 获取access_token的请求地址
		StringBuilder.append(accessTokenUrl).append("?").append("appid=")
				.append(appId).append("&").append("redirect_uri=").append(redirectUri).append("&").append("code=")
				.append(code).append("&").append("authorization_code");
		
	
		String result = ApiConnector.get(StringBuilder.toString(), null);
		log.info(">>>>>>>>>accessToken result:" + result);
		
		WXAuthenDTO authenDTO = JSON.parseObject(result, WXAuthenDTO.class);
		
		return authenDTO;
	}

	public WXUserInfoDTO getWxUserInfo(WXAuthenDTO authenDTO) {

		HttpGet userInfoGet = new HttpGet(wxUserInfoUrl+"?access_token="
				+ authenDTO.getAccess_token() + "&openid=" + authenDTO.getOpenid());
		CloseableHttpClient httpclient = HttpClients.createDefault();// 创建http客户端
		CloseableHttpResponse httpResponse;
		try {
			httpResponse = httpclient.execute(userInfoGet);
			String result = EntityUtils.toString(httpResponse.getEntity());
			log.info(">>>>>>>>>用户微信信息:" + result);

			WXUserInfoDTO wxInfo = JSON.parseObject(result, WXUserInfoDTO.class);
			log.info(">>>>>>>>>>>nickname=" + wxInfo.getNickname());
			return wxInfo;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		throw new BizException("获得用户信息失败");
		}

	}
}
