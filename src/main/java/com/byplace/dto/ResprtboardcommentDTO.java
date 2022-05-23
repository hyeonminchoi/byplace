package com.byplace.dto;

public class ResprtboardcommentDTO {
	long reportboardcomment_no, user_no;
	String reportboardcomment_comment, reportboardcomment_date;
	String user_id;
	
	public long getReportboardcomment_no() {
		return reportboardcomment_no;
	}
	public void setReportboardcomment_no(long reportboardcomment_no) {
		this.reportboardcomment_no = reportboardcomment_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getReportboardcomment_comment() {
		return reportboardcomment_comment;
	}
	public void setReportboardcomment_comment(String reportboardcomment_comment) {
		this.reportboardcomment_comment = reportboardcomment_comment;
	}
	public String getReportboardcomment_date() {
		return reportboardcomment_date;
	}
	public void setReportboardcomment_date(String reportboardcomment_date) {
		this.reportboardcomment_date = reportboardcomment_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
