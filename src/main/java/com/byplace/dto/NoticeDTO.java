package com.byplace.dto;

public class NoticeDTO {
	long notice_no, user_no;
	String notice_title, notice_comment, notice_date;
	int notice_del;
	String notice_orifilename, notice_filename;
	
	String user_id;
	String user_name;
	int totalcount;
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getNotice_orifilename() {
		return notice_orifilename;
	}
	public void setNotice_orifilename(String notice_orifilename) {
		this.notice_orifilename = notice_orifilename;
	}
	public String getNotice_filename() {
		return notice_filename;
	}
	public void setNotice_filename(String notice_filename) {
		this.notice_filename = notice_filename;
	}
	public long getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(long notice_no) {
		this.notice_no = notice_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_comment() {
		return notice_comment;
	}
	public void setNotice_comment(String notice_comment) {
		this.notice_comment = notice_comment;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public int getNotice_del() {
		return notice_del;
	}
	public void setNotice_del(int notice_del) {
		this.notice_del = notice_del;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
}
