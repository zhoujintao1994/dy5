package com.dayuan.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dayuan.handler.HandlerMapping2;


public class SpringContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("=============init begin==============");
		
		HandlerMapping2.init();
		
		System.out.println("=====================init end=========================");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(">>>>>> context destroy");
	}

}
