package com.dayuan.test;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dayuan.serviceimpl.AtmService;

public class ConcurrentTransferTest {
	
	static AtmService atmService;
	static final int max_thread_num = 100;
	static CyclicBarrier cb = new CyclicBarrier(max_thread_num);
	static CountDownLatch cd = new CountDownLatch(max_thread_num);
	
	static AtomicInteger errorCount = new AtomicInteger(0);//
	static AtomicInteger successCount = new AtomicInteger(0);

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
		atmService = context.getBean(AtmService.class);
		
		TransferRunnable runnable = new TransferRunnable();
		for (int i = 0; i < max_thread_num; i++) {
			new Thread(runnable, "线程" + i).start();
		}
		
		cd.await();
		
		System.out.println("错误：" + errorCount.get());
		System.out.println("成功：" + successCount.get());
	}

}

class TransferRunnable implements Runnable {
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + "等待");
			ConcurrentTransferTest.cb.await();
			ConcurrentTransferTest.atmService.outTransfer("1", "2222", "7753", "123");
			ConcurrentTransferTest.successCount.incrementAndGet();
		} catch (Exception e) {
			ConcurrentTransferTest.errorCount.incrementAndGet();
		}
		
		ConcurrentTransferTest.cd.countDown();
		
	}
}
