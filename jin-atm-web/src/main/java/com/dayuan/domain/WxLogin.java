package com.dayuan.domain;

import java.util.Date;

public class WxLogin {
	private Integer id;
	private Integer userId;
	private Date createTime;
	private Date modifyTime;
	private String acceccToken;
	private String refreshToken;
	private String openid;
	private Integer expiresIn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		 this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getAcceccToken() {
		return acceccToken;
	}

	public void setAcceccToken(String acceccToken) {
		this.acceccToken = acceccToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

}
