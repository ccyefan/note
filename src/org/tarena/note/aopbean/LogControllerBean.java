package org.tarena.note.aopbean;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component//1.扫描到Spring容器
@Aspect//2.将该组件指定为切面组件
public class LogControllerBean {
	
//	3.指定切入目标和时机
	//在执行Controller方法前执行下面逻辑
	@Before("within(org.tarena.note.controller..*)")
	public void logController(){
		System.out.println("--执行调用Controller请求处理方法--");
	}
	
	//在调用Dao组件方法前执行下面逻辑
//	@Before("bean(*Dao)")
	public void logDao(){
		System.out.println("--执行调用Dao方法--");
	}
	
}
