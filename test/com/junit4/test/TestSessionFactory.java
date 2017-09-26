package com.junit4.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSessionFactory {
//	@Test
//	public void test1(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		System.out.println(ctx);
//	}
	@Test
	public void test2(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory)ctx.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
}
