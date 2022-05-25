package com.byplace.dto;

public class BoardcommentDTO {
	long boardcomment_no, user_no;
	String boardcomment_title, boardcomment_comment, boardcomment_date;
	int boardcomment_del;
	int board_commentcount;
	

	String user_id;

	public int getBoard_commentcount() {
		return board_commentcount;
	}
	
	public void setBoard_commentcount(int board_commentcount) {
		this.board_commentcount = board_commentcount;
	}
	public long getBoardcomment_no() {
		return boardcomment_no;
	}

	public void setBoardcomment_no(long boardcomment_no) {
		this.boardcomment_no = boardcomment_no;
	}

	public long getUser_no() {
		return user_no;
	}

	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}

	public String getBoardcomment_title() {
		return boardcomment_title;
	}

	public void setBoardcomment_title(String boardcomment_title) {
		this.boardcomment_title = boardcomment_title;
	}

	public String getBoardcomment_comment() {
		return boardcomment_comment;
	}

	public void setBoardcomment_comment(String boardcomment_comment) {
		this.boardcomment_comment = boardcomment_comment;
	}

	public String getBoardcomment_date() {
		return boardcomment_date;
	}

	public void setBoardcomment_date(String boardcomment_date) {
		this.boardcomment_date = boardcomment_date;
	}

	public int getBoardcomment_del() {
		return boardcomment_del;
	}

	public void setBoardcomment_del(int boardcomment_del) {
		this.boardcomment_del = boardcomment_del;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
