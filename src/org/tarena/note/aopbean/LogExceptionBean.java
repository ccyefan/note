package org.tarena.note.aopbean;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogExceptionBean {

	//����ex��������Ŀ�귽���׳����쳣����
	@AfterThrowing(throwing="ex",
		pointcut="within(org.tarena.note.controller..*)")
	public void logException(Exception ex) throws IOException{
		//��¼�쳣��Ϣ
//		System.out.println("�������쳣��"+ex);
		FileWriter fw = new FileWriter("E:\\error.log",true);
		PrintWriter out = new PrintWriter(fw);
		out.println("============�쳣��Ϣ����============");
		ex.printStackTrace(out);//���쳣ջ��Ϣд��error.log
		out.flush();
		out.close();
		fw.close();
	}
	
}
