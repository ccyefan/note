package org.tarena.note.dao;

import org.tarena.note.entity.Book;

public interface BookMapperDao {
	//��������sql��������
	public Book findById(String id);
	//ͨ��һ��sql��������
	public Book findById1(String id);
}
