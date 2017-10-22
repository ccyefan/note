package org.tarena.note.service.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class JUnitTestDemo {
	
	@BeforeClass//�ڲ�����ִ�п�ʼʱֻ����һ��
	public static void mybefore(){
		System.out.println("--mybefore--");
	}
	@AfterClass//�ڲ�����ִ�н�βʱֻ����һ��
	public static void myafter(){
		System.out.println("--myafter--");
	}
	
	@Before//ÿ��testִ��ǰ����һ��
	public void myinit(){
		System.out.println("--myinit--");
	}
	
	@After//ÿ��testִ�к����һ��
	public void mydestroy(){
		System.out.println("--mydestroy--");
	}
	
	@Test
	public void f1(){
		System.out.println("--f1--");
		Assert.assertEquals(1, 0);
	}
	
	@Test
	public void f2(){
		System.out.println("--f2--");
		String s = null;
		s.length();
	}

}
