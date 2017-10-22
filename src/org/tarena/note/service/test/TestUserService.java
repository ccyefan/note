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
	
	@Before//ÿ��ִ��test������ִ�и÷���
	public void init(){
		String conf = "applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(conf);
		userService = ac.getBean(
			"userService",UserService.class);
	}
	
	@Test//ִ�����
	public void test1(){
		//
		NoteResult result = 
			  userService.checkLogin("tom", "123");
		//����
		Assert.assertEquals(1, result.getStatus());
		Assert.assertEquals("�û���������", result.getMsg());
		Assert.assertNull(result.getData());
	}
	
	@Test//ִ�����
	public void test2(){
		NoteResult result = 
			  userService.checkLogin("demo", "123");
		//����
		Assert.assertEquals(2, result.getStatus());
		Assert.assertEquals("���벻��ȷ", result.getMsg());
		Assert.assertNull(result.getData());
	}
	
	@Test//ִ�����
	public void test3(){
		NoteResult result = 
			  userService.checkLogin("demo", "1234");
		//����
		Assert.assertEquals(0, result.getStatus());
		Assert.assertEquals("��¼�ɹ�", result.getMsg());
		Assert.assertNull(result.getData());
	}
	
	@After//ÿ��ִ��test������������ø÷���
	public void destroy(){
		userService = null;
		ac.close();
	}
	
}



