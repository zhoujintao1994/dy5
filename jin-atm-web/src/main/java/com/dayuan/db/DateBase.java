package com.dayuan.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DateBase {

	private static SqlSessionFactory sy = null;
	static {

		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			sy = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 默认开启事务
	 * 
	 * @return
	 */
	public static SqlSession getSession() {
		return sy.openSession();
	}

	public static SqlSession getSession(boolean autuCommit) {
		return sy.openSession(autuCommit);
	}
}
