package com.dayuan.dto;



public class AjaxDTO {
	private boolean success;//�Ƿ�ɹ�
	private String message;//��Ϣ
	private Object data;//����
	
	private AjaxDTO(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	private AjaxDTO(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}
	
	public static AjaxDTO success() {
		return new AjaxDTO(true, "");//?
	}
	
	public static AjaxDTO success(Object data) {
		return new AjaxDTO(true, data);
	}
	
	public static AjaxDTO faild(String message) {
		return new AjaxDTO(false, message);
	}
	
	public boolean isSuccess() {
		return success;
	}
	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
	
}
