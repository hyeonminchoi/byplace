package com.byplace.dto;

public class UserlogDTO {
	private long userlog_no, user_no;
	private String userlog_status, userlog_date, user_id;
	
	public long getUserlog_no() {
		return userlog_no;
	}
	public void setUserlog_no(long userlog_no) {
		this.userlog_no = userlog_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getUserlog_status() {
		return userlog_status;
	}
	public void setUserlog_status(String userlog_status) {
		this.userlog_status = userlog_status;
	}
	public String getUserlog_date() {
		return userlog_date;
	}
	public void setUserlog_date(String userlog_date) {
		this.userlog_date = userlog_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
