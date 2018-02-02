package com.dayuan.domain;


public class Transfer {
	
	private Integer id;
	private String tfBlance;
	private String tfBankcard;
	private String cfBankcard;
	private String createTime;
	private String modifyTime;
	private Integer status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTfBlance() {
		return tfBlance;
	}
	public void setTfBlance(String tfBlance) {
		this.tfBlance = tfBlance;
	}
	public String getTfBankcard() {
		return tfBankcard;
	}
	public void setTfBankcard(String tfBankcard) {
		this.tfBankcard = tfBankcard;
	}
	public String getCfBankcard() {
		return cfBankcard;
	}
	public void setCfBankcard(String cfBankcard) {
		this.cfBankcard = cfBankcard;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	


	
}
