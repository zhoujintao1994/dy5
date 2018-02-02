package com.dayuan.domain;

import java.io.Serializable;
import java.sql.Date;

public class BankCard implements Serializable{

	private Integer id;
	private String cardNumber;
	private String passWord;
	private String sum;
	private Date grearTime;
	private Date mtime;
	private Integer version;
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public Date getGrearTime() {
		return grearTime;
	}
	public void setGrearTime(Date grearTime) {
		this.grearTime = grearTime;
	}
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "BankCard [id=" + id + ", cardNumber=" + cardNumber + ", passWord=" + passWord + ", sum=" + sum
				+ ", grearTime=" + grearTime + ", mtime=" + mtime + ", version=" + version + ", userId=" + userId + "]";
	}
	
	
	

}
