package com.dayuan.websocket.handler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.dayuan.domain.Message;
import com.dayuan.domain.User;
import com.dayuan.mapper.MessageMapper;



@Component("atmWebSocketHandler")//继承TextWebSocketHandler 这个类 并实现其中的方法
public class ATMWebSocketHandler extends TextWebSocketHandler {
	//线程安全的装键值对的map集合
	private static final ConcurrentHashMap<Integer, WebSocketSession> map = new ConcurrentHashMap<>();
	
	@Autowired
	private MessageMapper messageMapper;
	//发送信息要有对应的userid（人）  和信息
	public void sendMessage(int userId, String message) {
		try {
			//持久化到数据库
			Message addMessage = new Message();
			addMessage.setMsg(message);
			addMessage.setStatus(0);
			addMessage.setUserId(userId);
			messageMapper.add(addMessage);
			
		//发送消息给客户端
		WebSocketSession session = map.get(userId);//获得http协议里面的session里的userId
		if (null == session) {
			return;
		}

		//这个session里面发送一个（文本信息）
			session.sendMessage(new TextMessage(message));
		} catch (IOException e) {
			map.remove(userId);//失败就忘掉这个session的userId
		}
	}

	@Override//建立连接的方法
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Object obj = (Object) session.getAttributes().get("user");//获取登录时的设置的session的信息
		if (null == obj) {
			System.out.println("没有session");
			return;
		}

		User user = (User) obj;

		map.put(user.getId(), session);//在map里面放一个键值对
		System.out.println("服务器端Websocket连接成功" + user.getUsername());
	}

	@Override//处理文字信息
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("客户端发送的消息：" + message.getPayload());
		
		//服务端接到客户端信息，进行回应
		session.sendMessage(new TextMessage("world"));
		
		//延迟五秒回复信息
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					session.sendMessage(new TextMessage("thread world"));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} ).start();
	}

	@Override//连接关闭后
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Object obj = (Object) session.getAttributes().get("user");
		if (null == obj) {
			System.out.println("没有session");
			return;
		}

		User user = (User) obj;
		map.remove(user.getId());//关掉这个session
	}

	@Override//处理传输错误
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		Object obj = (Object) session.getAttributes().get("user");
		if (null == obj) {
			System.out.println("没有session");
			return;
		}

		User user = (User) obj;
		map.remove(user.getId());//关掉这个session

	}
}