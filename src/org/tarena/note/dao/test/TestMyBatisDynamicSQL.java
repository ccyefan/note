package org.tarena.note.dao.test;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBean;

public class TestMyBatisDynamicSQL {
	private NoteMapperDao noteDao;
	
	@Before
	public void init(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		noteDao = ac.getBean(
			"noteMapperDao",NoteMapperDao.class);
	}
	
//	@Test
	public void test1(){
		NoteBean params = new NoteBean();
		params.setStatus("2");
		//params.setTitle("spring");
		//Date dt = Date.valueOf("2015-10-01");
		//params.setBeginDate(dt.getTime());
		//params.setEndDate(dt.getTime());
		List<Note> list = 
			noteDao.findNotes(params);
		for(Note note : list){
			System.out.println(note.getCn_note_title());
		}
		System.out.println("结果数量:"+list.size());
	}
	
	//测试动态更新
//	@Test
	public void test2(){
		Note note = new Note();
//		note.setCn_note_id("fsaf-as-df-asdf-as-df-dsa");
//		note.setCn_note_title("动态更新");
//		note.setCn_note_body("使用<set>标记");
//		note.setCn_note_last_modify_time(
//			System.currentTimeMillis());
		note.setCn_note_status_id("2");
		note.setCn_note_id("fsaf-as-df-asdf-as-df-dsa");
		int rows = noteDao.updateNote(note);
		System.out.println(rows);
	}
	
	//测试批量删除
	@Test
	public void test3(){
		String[] ids = 
			{"ss19055-30e8-4cdc-bfac-97c6bad9518f",
			"fsaf-as-df-asdf-as-df-dsa",
			"ffc2cf21-78ed-4647-adb4-3e545613ef26"};
		int rows = noteDao.deleteNotes(ids);
		System.out.println("删除的记录数量:"+rows);
	}
	
	
	
}
