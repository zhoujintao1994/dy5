package com.dayuan.handler;

import java.util.HashMap;
import java.util.Map;

import com.dayuan.controller.BankCardController;
import com.dayuan.controller.BaseController;
import com.dayuan.controller.UserController;



public class ControllerHandler {
	
	private static final Map<String, BaseController> conMap = new HashMap<String, BaseController>();
	
	static {
		conMap.put("user", new UserController());
		conMap.put("card", new BankCardController());
	}

	public static BaseController getController(String key) {
		return conMap.get(key);
	}
}
