package com.dayuan.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "/spring.xml" })
public class SpringTest {
	@Autowired
	private RedisTemplate<String, String> template;// new一个spring.xml中的（RedisTemplate）连接工厂类来管理

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Person> personRedisTmp;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, Object> personRedisTmp1;

	@Resource(name = "redisTemplate")
	private ListOperations<String, Person> personRedisTmp2;

	@Test
	public void testSet() {
		//1
		template.opsForValue().set("user:1005", "jim");
		String user = template.opsForValue().get("user:1005");
		System.out.println(user);
		//2
		template.opsForHash().put("person:10025", "username", "p03");
		template.opsForHash().put("person:10025", "age", "88");
		//3
		
		personRedisTmp2.leftPush("list:person", new Person());
	}
}
