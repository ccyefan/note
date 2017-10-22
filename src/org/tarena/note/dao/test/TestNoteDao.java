package org.tarena.note.dao.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;

public class TestNoteDao {
	
	private NoteMapperDao noteDao;
	
	@Before
	public void init(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		noteDao = ac.getBean(
			"noteMapperDao",NoteMapperDao.class);
	}
	
	@Test
	public void test1(){
		List<Map<String,Object>> list = 
			noteDao.findByBookId("d0b0727f-a233-4a1f-8600-f49fc1f25bc9");
		for(Map note : list){
			System.out.println(note.get("cn_note_title"));
		}
	}
	
	@Test
	public void test2(){
		List<Note> list = noteDao.findAll();
		for(Note n : list){
			System.out.println(n.getCn_note_id()+" "
				+n.getCn_note_title()+" "
				+n.getBook().getCn_notebook_name());
		}
	}
	
	
	
}
