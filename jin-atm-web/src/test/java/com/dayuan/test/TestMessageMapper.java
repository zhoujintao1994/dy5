package com.dayuan.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuan.domain.Message;
import com.dayuan.mapper.MessageMapper;
import com.dayuan.mapper.UserMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "/spring.xml" })
@Transactional
public class TestMessageMapper {

	@Autowired
	private MessageMapper messageMapper;

	
	private Message message;

	@Test
	public void countMessage() {
		Message message =  new Message();
		message.setId(1);
		message.setMsg("dsd");
		message.setUserId(2);
		message.setStatus(0);
		int rows = messageMapper.countMessage(2);
		assertEquals(1, rows);
	}
	
	@Test
	public void updateStatus() {
		int rows = messageMapper.updateStatus(0, 2);
		assertEquals(1, rows);
	}
	
}
