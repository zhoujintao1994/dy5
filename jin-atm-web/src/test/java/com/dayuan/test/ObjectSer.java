package com.dayuan.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectSer {

	public static void main(String[] args) throws Exception {
//		Dog dog = new Dog();
//		dog.setAge(10);
//		dog.setName("wc007");
//		
		String username = "wc007";
		//Ð´
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/dog.txt"));
		oos.writeObject(username);
		oos.close();  
		//¶Á
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/dog.txt"));
		username = (String)ois.readObject();
		System.out.println(username);
		
		
		
	}

}

class Dog implements Serializable {
	private int age;
	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
