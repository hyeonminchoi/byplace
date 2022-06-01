package com.byplace.dto;

public class ReportboardcommentDTO {
	long reportboardcomment_no, user_no;
	String reportboardcomment_comment, reportboardcomment_date;
	String user_name;
	String user_id;
	int reportboardcomment_del;
	int reportboard_commentcount;
	int reportboard_no;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getReportboardcomment_del() {
		return reportboardcomment_del;
	}
	public void setReportboardcomment_del(int reportboardcomment_del) {
		this.reportboardcomment_del = reportboardcomment_del;
	}
	public int getReportboard_commentcount() {
		return reportboard_commentcount;
	}
	public void setReportboard_commentcount(int reportboard_commentcount) {
		this.reportboard_commentcount = reportboard_commentcount;
	}
	public int getReportboard_no() {
		return reportboard_no;
	}
	public void setReportboard_no(int reportboard_no) {
		this.reportboard_no = reportboard_no;
	}
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
