package com.dayuan.utill;

public class pageHelper {
	/**
	 * 总行数
	 */
	private int totalCount;
	/**
	 * 单页行数
	 */
	private int pageNum = 5;
	/**
	 * 当前页
	 */
	private int currentPageIndex = 1;
	/**
	 * 总页数
	 */
	private int totalPages;
	/**
	 * 开始索引
	 */
	private int startIndex = 0;

	public pageHelper() {
		// TODO Auto-generated constructor stub
	}

	public pageHelper(Integer currentPageIndex, Integer totalCount) {
		this.currentPageIndex = currentPageIndex;
		this.totalCount = totalCount;

		/**
		 * 计算总页数
		 */
		if (totalCount % pageNum == 0) {
			totalPages = totalCount / pageNum;
		} else {
			totalPages = totalCount / pageNum + 1;
		}

		/**
		 * 计算开始索引
		 */
		startIndex = (currentPageIndex - 1) * pageNum;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

}
