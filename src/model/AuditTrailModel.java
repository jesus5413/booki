package model;

import java.util.Date;

public class AuditTrailModel {
	private int id;
	private java.sql.Timestamp dateAdded;
	private String msg;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(java.sql.Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
