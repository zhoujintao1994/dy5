package com.dayuan.test;



import java.io.Serializable;

import com.alibaba.fastjson.JSON;


import redis.clients.jedis.Jedis;

public class RedistTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.8.184", 6379);
		
		Person person = new Person();
		person.setAge(10);
		person.setUsername("jim");
		
		String personJson = JSON.toJSONString(person);//jeson½âÎöµÄ×Ö·û´®
		System.out.println(personJson);
		
		jedis.set("person:1002", personJson);
		System.out.println(jedis.get("person:1002"));
		
		String personString = jedis.get("person:1002");
		Person person2 = JSON.parseObject(personString, Person.class);
		System.out.println(person2.getAge() + "  " + person2.getUsername());
		
	}
		
	}

class Person implements Serializable{
	private int age;
	private String username ;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
}
