package com.dayuan.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuan.mapper.UserMapper;


@RunWith(SpringRunner.class)
@ContextConfiguration({"/spring/spring.xml"})
@Transactional
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testModifyPassword() {
		int rows = userMapper.modifyPwd("q1", "68");
		assertEquals(1, rows);
	}

}


