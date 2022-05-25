package com.byplace.dto;

public class BoardDTO {
	long board_no, user_no;
	String board_title, board_comment, board_date;
	int board_del, board_count;
	int board_commentcount;
	
	String user_id; //사용자 id
	String user_name;
	int user_status; // 사용자의 상태 확인(-1: 블랙상태일 경우 게시물 안 보이게 설정)
	int totalcount; //총 게시물 수
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public int getBoard_commentcount() {
		return board_commentcount;
	}
	public void setBoard_commentcount(int board_commentcount) {
		this.board_commentcount = board_commentcount;
	}
	
	public long getBoard_no() {
		return board_no;
	}
	public void setBoard_no(long board_no) {
		this.board_no = board_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_comment() {
		return board_comment;
	}
	public void setBoard_comment(String board_comment) {
		this.board_comment = board_comment;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public int getBoard_del() {
		return board_del;
	}
	public void setBoard_del(int board_del) {
		this.board_del = board_del;
	}
	public int getBoard_count() {
		return board_count;
	}
	public void setBoard_count(int board_count) {
		this.board_count = board_count;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	
}
