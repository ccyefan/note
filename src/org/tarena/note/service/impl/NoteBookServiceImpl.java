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

@Service("bookService")//ɨ��
public class NoteBookServiceImpl 
	implements NoteBookService{
	
	@Resource//ע��
	private NoteBookMapperDao bookDao;

	public NoteResult loadBooks(String userId) {
		NoteResult result = new NoteResult();
		
		List<NoteBook> list = 
			bookDao.findByUserId(userId);
		result.setData(list);
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
		return result;
	}

	public NoteResult add(String userId, String bookName) {
		NoteResult result = new NoteResult();
		//��������װ��book����
		NoteBook book = new NoteBook();
		book.setCn_notebook_name(bookName);//���ñʼǱ���
		book.setCn_user_id(userId);//�����û�ID
		//���ʼǱ������Ƿ��Ѵ���
		NoteBook has_book = 
			bookDao.findByNameAndUser(book);
		if(has_book != null){
			result.setStatus(1);
			result.setMsg("�Ѵ��ڸ����ƱʼǱ�");
			return result;
		}
		//��Ӵ���
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);//���ñʼǱ�ID
		book.setCn_notebook_desc("");
		Timestamp time = 
			new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);//����ϵͳ��ǰʱ��
		book.setCn_notebook_type_id("5");//��������Ϊnormal
		bookDao.save(book);//���
		
		result.setStatus(0);
		result.setMsg("��ӱʼǱ��ɹ�");
		result.setData(bookId);//���½��ʼǱ�ID����ȥ
		return result;
	}

}
