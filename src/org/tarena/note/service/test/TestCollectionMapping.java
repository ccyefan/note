package org.tarena.note.service.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.BookMapperDao;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Book;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBook;

public class TestCollectionMapping {
	
	
	@Test//未使用关联映射
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		NoteBookMapperDao bookDao = 
			ac.getBean("noteBookMapperDao",
			NoteBookMapperDao.class);
		NoteBook book = 
			bookDao.findById(
				"fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		System.out.println("====笔记本信息====");
		System.out.println(book.getCn_notebook_name());
		System.out.println("===包含的笔记列表===");
		NoteMapperDao noteDao = ac.getBean(
			"noteMapperDao",NoteMapperDao.class);
		List<Map<String, Object>> list = 
			noteDao.findByBookId(
				"fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		for(Map note:list){
			System.out.println(note.get("cn_note_id")+" "
				+note.get("cn_note_title"));
		}
	}
	
	@Test//使用关联映射
	public void test2(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		BookMapperDao bookDao = 
			ac.getBean("bookMapperDao",
			BookMapperDao.class);
		
		Book book = bookDao.findById1(
				"fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		System.out.println("====笔记本信息====");
		System.out.println(book.getCn_notebook_name());
		System.out.println("===包含的笔记列表===");
		List<Note> list = book.getNotes();
		for(Note note:list){
			System.out.println(note.getCn_note_title());
		}
	}
	
}
