package org.tarena.note.dao;

import java.util.List;

import org.tarena.note.entity.NoteBook;

public interface NoteBookMapperDao {
	public NoteBook findById(String id);
	public List<NoteBook> findByUserId(String userId);
	public int save(NoteBook book);
	public NoteBook findByNameAndUser(NoteBook book);
}
