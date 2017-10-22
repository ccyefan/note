package org.tarena.note.dao;

import org.tarena.note.entity.Book;

public interface BookMapperDao {
	//调用两个sql加载数据
	public Book findById(String id);
	//通过一个sql加载数据
	public Book findById1(String id);
}
