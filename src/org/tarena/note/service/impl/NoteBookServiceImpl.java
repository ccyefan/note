package org.tarena.note.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteUtil;

@Service("bookService")//扫描
public class NoteBookServiceImpl 
	implements NoteBookService{
	
	@Resource//注入
	private NoteBookMapperDao bookDao;

	public NoteResult loadBooks(String userId) {
		NoteResult result = new NoteResult();
		
		List<NoteBook> list = 
			bookDao.findByUserId(userId);
		result.setData(list);
		result.setStatus(0);
		result.setMsg("查询成功");
		return result;
	}

	public NoteResult add(String userId, String bookName) {
		NoteResult result = new NoteResult();
		//将参数封装成book对象
		NoteBook book = new NoteBook();
		book.setCn_notebook_name(bookName);//设置笔记本名
		book.setCn_user_id(userId);//设置用户ID
		//检测笔记本名称是否已存在
		NoteBook has_book = 
			bookDao.findByNameAndUser(book);
		if(has_book != null){
			result.setStatus(1);
			result.setMsg("已存在该名称笔记本");
			return result;
		}
		//添加处理
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);//设置笔记本ID
		book.setCn_notebook_desc("");
		Timestamp time = 
			new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);//设置系统当前时间
		book.setCn_notebook_type_id("5");//设置类型为normal
		bookDao.save(book);//添加
		
		result.setStatus(0);
		result.setMsg("添加笔记本成功");
		result.setData(bookId);//将新建笔记本ID传出去
		return result;
	}

}
