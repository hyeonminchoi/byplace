package com.byplace.dto;

public class ReportboardDTO {
	long reportboard_no, user_no;
	String reportboard_title, reportboard_comment, reportboard_date;
	int reportboard_del;
	int reportboard_count, reportboard_commentcount;
	String user_id;
	String user_name;
	int user_status; 
	int totalcount; 

	
	
	public int getReportboard_count() {
		return reportboard_count;
	}

	public void setReportboard_count(int reportboard_count) {
		this.reportboard_count = reportboard_count;
	}

	public int getReportboard_commentcount() {
		return reportboard_commentcount;
	}

	public void setReportboard_commentcount(int reportboard_commentcount) {
		this.reportboard_commentcount = reportboard_commentcount;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUser_status() {
		return user_status;
	}

	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public long getReportboard_no() {
		return reportboard_no;
	}

	public void setReportboard_no(long reportboard_no) {
		this.reportboard_no = reportboard_no;
	}

	public long getUser_no() {
		return user_no;
	}

	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}

	public String getReportboard_title() {
		return reportboard_title;
	}

	public void setReportboard_title(String reportboard_title) {
		this.reportboard_title = reportboard_title;
	}

	public String getReportboard_comment() {
		return reportboard_comment;
	}

	public void setReportboard_comment(String reportboard_comment) {
		this.reportboard_comment = reportboard_comment;
	}

	public String getReportboard_date() {
		return reportboard_date;
	}

	public void setReportboard_date(String reportboard_date) {
		this.reportboard_date = reportboard_date;
	}

	public int getReportboard_del() {
		return reportboard_del;
	}

	public void setReportboard_del(int reportboard_del) {
		this.reportboard_del = reportboard_del;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
