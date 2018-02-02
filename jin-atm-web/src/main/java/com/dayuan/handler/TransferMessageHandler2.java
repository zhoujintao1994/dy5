package com.dayuan.handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuan.websocket.handler.ATMWebSocketHandler;

@Component
public class TransferMessageHandler2 {

	// 单例执行调度程序
	private static final ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	@Autowired
	private ATMWebSocketHandler atmWebSocketHandler;

	public void sendMessage(int userId, String msg) {
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				atmWebSocketHandler.sendMessage(userId, msg);
			}
		});
	}
}
