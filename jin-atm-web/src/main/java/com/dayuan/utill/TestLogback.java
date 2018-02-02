package com.dayuan.utill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogback {
	static Logger log = LoggerFactory.getLogger(TestLogback.class);
	
	public static void main(String[] args) {
		log.trace("hello,trace");
		
		log.debug("hello,debug");
		
		log.info("hello,info");
		
		log.warn("hello,warn");
		
		log.error("hello,error");
		
		System.out.println("hello");
	}
     
}
