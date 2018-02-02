package com.dayuan.dto;


import java.util.List;

public class MessageDTO {
	private Integer countMessage;
	private List<Messageinfo> list;
	
	public Integer getCountMessage() {
		return countMessage;
	}
	
	public void setCountMessage(Integer countMessage) {
		this.countMessage = countMessage;
	}

	public List<Messageinfo> getList() {
		return list;
	}

	public void setList(List<Messageinfo> list) {
		this.list = list;
	}


	public static class Messageinfo {

		 private Integer status;
		 private String msg;
		 private long createTime;
		 
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public long getCreateTime() {
			return createTime;
		}
		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}
		 
	}
	
}


