package org.tarena.note.entity;

import java.io.Serializable;

public class NoteBean implements Serializable{
	private String title;
	private String status = "0";
	private Long beginDate;
	private Long endDate;
	public String getTitle() {
		//如果title有值前后追加%
		if(title != null){
			return "%"+title+"%";
		}else{
			return title;
		}
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Long beginDate) {
		this.beginDate = beginDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	
}
