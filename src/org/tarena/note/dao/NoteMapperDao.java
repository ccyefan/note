package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBean;

public interface NoteMapperDao {
	public List<Note> findAll();
	
	public int deleteNotes(String[] ids);
	
	public int updateNote(Note note);
	
	public List<Note> findNotes(NoteBean bean);
	
	public int updateStatus(String noteId);
	
	/**
	 * ���±ʼǱ���,����,�޸�ʱ��
	 * @param note
	 * @return
	 */
	public int update(Note note);
	public List<Map<String, Object>> findByBookId(String bookId);
	public int save(Note note);
	/**
	 * ���ݱʼ�ID��ѯ���������
	 * @param noteId
	 * @return ���ر���cn_note_title������cn_note_body
	 */
	public Map<String,Object> findById(String noteId);
}


