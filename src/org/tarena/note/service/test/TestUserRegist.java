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
	
	//�����û����ظ�
	@Test
	public void test1(){
		NoteResult result = 
			userService.regist(
				"demo", "123", "��è");
		//����
		Assert.assertEquals(
			1, result.getStatus());
		Assert.assertEquals(
			"�û����ѱ�ռ��",result.getMsg());
	}
	
	//����ע��ɹ�,��Ч��һ��
	@Test
	public void test2(){
		NoteResult result = 
			userService.regist(
			  "tarena", "123456", "����");
		//����
		Assert.assertEquals(
			0, result.getStatus());
		Assert.assertEquals(
			"ע��ɹ�", result.getMsg());
	}
	
}
