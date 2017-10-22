package org.tarena.note.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.dao.ShareMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.SearchBean;
import org.tarena.note.entity.Share;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;

@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService{

	@Resource
	private NoteMapperDao noteDao;
	@Resource
	private ShareMapperDao shareDao;
	
	@Transactional(readOnly=true)
	public NoteResult loadNotes(String bookId) {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> list = 
			noteDao.findByBookId(bookId);
		result.setData(list);
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
		return result;
	}

	public NoteResult add(String userId, String bookId, String noteTitle) {
		NoteResult result = new NoteResult();
		//TODO���ʼ������Ƿ��ظ�
		//����note����
		Note note = new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(noteTitle);
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("1");//normal
		note.setCn_note_type_id("1");//normal
		note.setCn_note_body("");
		note.setCn_note_create_time(
			System.currentTimeMillis());
		note.setCn_note_last_modify_time(null);
		//����dao���
		noteDao.save(note);
		result.setStatus(0);
		result.setMsg("�����ʼǳɹ�");
		result.setData(noteId);//���رʼ�ID
		return result;
	}

	@Transactional(readOnly=true)
	public NoteResult loadNote(String noteId) {
		NoteResult result = new NoteResult();
		
		Map data = noteDao.findById(noteId);
		result.setData(data);
		result.setStatus(0);
		result.setMsg("��ѯ�ʼǳɹ�");
		return result;
	}

	public NoteResult updateNote(
			String noteId, String noteTitle,
			String noteBody) {
		NoteResult result = new NoteResult();
		//TODO�������Ƿ��ظ�
		//����dao����
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(
			System.currentTimeMillis());
		
		noteDao.update(note);//ִ�и���
		result.setStatus(0);
		result.setMsg("����ʼ�����");
		return result;
	}

	public NoteResult removeNote(String noteId) {
		NoteResult result = new NoteResult();
		
		noteDao.updateStatus(noteId);//�޸�Ϊɾ��״̬
		
		result.setStatus(0);
		result.setMsg("ɾ���ʼǳɹ�");
		return result;
	}

	public NoteResult share(String noteId) {
		
		NoteResult result = new NoteResult();
		//���ñʼ��Ƿ�����
		Share has_share = 
			shareDao.findByNoteId(noteId);
		if(has_share != null){
			result.setStatus(1);
			result.setMsg("�ñʼ��ѱ������");
			return result;
		}
		
		//����noteId��ȡ����ʼǵı��������
		Map<String,Object> note = 
			noteDao.findById(noteId);
		String noteTitle = (String)note.get("cn_note_title");
		String noteBody = (String)note.get("cn_note_body");
		//����dao����
		Share share = new Share();
		//����share����ֵ
		share.setCn_note_id(noteId);
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);
		share.setCn_share_title(noteTitle);
		share.setCn_share_body(noteBody);
		shareDao.save(share);
		
		result.setStatus(0);
		result.setMsg("����ʼǳɹ�");
		return result;
	}

	@Transactional(readOnly=true)
	public NoteResult search(String title,int page) {
		NoteResult result = new NoteResult();
		//��������
		if(title==null || "".equals(title.trim())){
			title = "%";
		}else{
			title = "%"+title+"%";
		}
		//����dao����
		SearchBean bean = new SearchBean();
		bean.setTitle(title);
		int begin = (page-1)*10;
		bean.setBegin(begin);
		System.out.println("===="+title);
		System.out.println("===="+page+":"+begin);
		List list = 
			shareDao.findLikeTitle(bean);
		result.setData(list);
		result.setStatus(0);
		result.setMsg("�����ɹ�");
		return result;
	}

}
