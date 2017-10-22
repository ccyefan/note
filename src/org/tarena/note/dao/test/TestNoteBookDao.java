package org.tarena.note.dao.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;

public class TestNoteBookDao {
	
	private NoteBookMapperDao bookDao;
	
	@Before
	public void init(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		bookDao = ac.getBean(
			"noteBookMapperDao",NoteBookMapperDao.class);
	}
	
	@Test
	public void test1(){
		List<NoteBook> list = 
			bookDao.findByUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(NoteBook book : list){
			System.out.println(book.getCn_notebook_name());
		}
	}
	
}
