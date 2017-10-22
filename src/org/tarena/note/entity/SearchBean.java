package org.tarena.note.entity;

import java.io.Serializable;

public class SearchBean implements Serializable{
	private String title;
	private int begin = 0;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	
}
