package com.dayuan.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HandlerMapping2 {
	private static ApplicationContext context;
	
	public static void init() {
		context = new ClassPathXmlApplicationContext(new String[] {"/spring/spring.xml"});
	}
	
	public static Object getController(String key) {
		return context.getBean(key);
	}
	
}
