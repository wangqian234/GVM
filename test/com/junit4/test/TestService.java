package com.junit4.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dt.entity.User;
import com.dt.service.UserService;

public class TestService {
	private ApplicationContext ctx;
	@Before
	public void test1(){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
//	@Test
//	public void test2(){
//		UserService userService=(UserService)ctx.getBean("userService");
//		System.out.println(userService);
//	}
	@Test
	public void test3(){
		UserService userService=(UserService)ctx.getBean("userService");
//		for(int i=0;i<500;i++){
//		User user = new User();
//		user.setName("我们是第"+i+"害虫");
//		user.setPwd("www.baidu.com");
//		userService.saveOrUpdate(user);
//		}
	}
}
