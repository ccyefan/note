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
	 * 更新笔记标题,内容,修改时间
	 * @param note
	 * @return
	 */
	public int update(Note note);
	public List<Map<String, Object>> findByBookId(String bookId);
	public int save(Note note);
	/**
	 * 根据笔记ID查询标题和内容
	 * @param noteId
	 * @return 返回标题cn_note_title和内容cn_note_body
	 */
	public Map<String,Object> findById(String noteId);
}


