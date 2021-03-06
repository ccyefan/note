package org.tarena.note.service.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

public class TestUserService {
	private AbstractApplicationContext ac;
	private UserService userService;
	
	@Before//每次执行test方法先执行该方法
	public void init(){
		String conf = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(conf);
		userService = ac.getBean(
			"userService",UserService.class);
	}
	
	@Test//执行入口
	public void test1(){
		//
		NoteResult result = 
			  userService.checkLogin("tom", "123");
		//断言
		Assert.assertEquals(1, result.getStatus());
		Assert.assertEquals("用户名不存在", result.getMsg());
		Assert.assertNull(result.getData());
	}
	
	@Test//执行入口
	public void test2(){
		NoteResult result = 
			  userService.checkLogin("demo", "123");
		//断言
		Assert.assertEquals(2, result.getStatus());
		Assert.assertEquals("密码不正确", result.getMsg());
		Assert.assertNull(result.getData());
	}
	
	@Test//执行入口
	public void test3(){
		NoteResult result = 
			  userService.checkLogin("demo", "1234");
		//断言
		Assert.assertEquals(0, result.getStatus());
		Assert.assertEquals("登录成功", result.getMsg());
		Assert.assertNull(result.getData());
	}
	
	@After//每次执行test方法结束后调用该方法
	public void destroy(){
		userService = null;
		ac.close();
	}
	
}



