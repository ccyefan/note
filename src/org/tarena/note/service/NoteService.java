package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;

public interface NoteService {
	public NoteResult search(String title,int page);
	public NoteResult loadNotes(String bookId);
	public NoteResult add(
		String userId,String bookId,String noteTitle);
	public NoteResult loadNote(String noteId);
	public NoteResult updateNote(
		String noteId,String noteTitle,String noteBody);
	public NoteResult removeNote(String noteId);
	public NoteResult share(String noteId);
	
	
}
