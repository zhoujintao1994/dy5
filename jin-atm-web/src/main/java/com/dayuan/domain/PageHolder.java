package com.dayuan.domain;

public class PageHolder {
	
	public static final int PRE_PAGE_NUM = 3;
	
	private int currentPageNum = 1;//当前页
	private int totalPageNum;//总页数
	private Object obj;
	private int offset = 0;//偏移量
	private int totalElementNum;//总条数
	
	public PageHolder(int currentPageNum, int totalElementNum) {
		this.currentPageNum = currentPageNum;
		this.totalElementNum = totalElementNum;
	}
	
	
	public int getOffset() {
		return (currentPageNum - 1) * PRE_PAGE_NUM;
	}
	
	public int getTotalPageNum() {//获得总页数
		return totalElementNum % PRE_PAGE_NUM == 0 ? totalElementNum / PRE_PAGE_NUM : totalElementNum / PRE_PAGE_NUM + 1;
	}


	public Object getObj() {
		return obj;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}


	public int getCurrentPageNum() {
		return currentPageNum;
	}

	
}
