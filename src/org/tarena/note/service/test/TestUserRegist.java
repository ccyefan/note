package org.tarena.note.service.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

public class TestUserRegist {
	
//	private UserMapperDao userDao;
	private UserService userService;
	
	@Before
	public void init(){
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(
				"applicationContext.xml");
//		userDao = ac.getBean(
//			"userMapperDao",UserMapperDao.class);
		userService = ac.getBean(
			"userService",UserService.class);
	}
	
	//测试用户名重复
	@Test
	public void test1(){
		NoteResult result = 
			userService.regist(
				"demo", "123", "大猫");
		//断言
		Assert.assertEquals(
			1, result.getStatus());
		Assert.assertEquals(
			"用户名已被占用",result.getMsg());
	}
	
	//测试注册成功,有效性一次
	@Test
	public void test2(){
		NoteResult result = 
			userService.regist(
			  "tarena", "123456", "达内");
		//断言
		Assert.assertEquals(
			0, result.getStatus());
		Assert.assertEquals(
			"注册成功", result.getMsg());
	}
	
}
