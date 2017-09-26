package com.junit4.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dt.controller.UserAction;

public class TestController {
	private ApplicationContext ctx;
	@Before
	public void test1(){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
//	@Test
//	public void test2(){
//		UserAction userAction=(UserAction)ctx.getBean("userAction");
//		System.out.println(userAction);
//	}
	@Test
	public void test3(){
		UserAction userAction=(UserAction)ctx.getBean("userAction");
//		userAction.save(null, null);
	}
}
