package com.byplace.dto;

public class NoticecommentDTO {
	long noticecomment_no, user_no, notice_no;
	String noticecomment_comment, noticecomment_date;
	
	String user_id;

	public long getNoticecomment_no() {
		return noticecomment_no;
	}

	public void setNoticecomment_no(long noticecomment_no) {
		this.noticecomment_no = noticecomment_no;
	}

	public long getUser_no() {
		return user_no;
	}

	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}

	public long getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(long notice_no) {
		this.notice_no = notice_no;
	}

	public String getNoticecomment_comment() {
		return noticecomment_comment;
	}

	public void setNoticecomment_comment(String noticecomment_comment) {
		this.noticecomment_comment = noticecomment_comment;
	}

	public String getNoticecomment_date() {
		return noticecomment_date;
	}

	public void setNoticecomment_date(String noticecomment_date) {
		this.noticecomment_date = noticecomment_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
