package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;

public interface NoteBookService {
	public NoteResult loadBooks(String userId);
	public NoteResult add(String userId,String bookName);
}
