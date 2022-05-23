package com.byplace.dto;

public class ChattingDTO {
	long chatting_no, user_no;
	String chatting_message, chatting_date;
	String user_id;
	
	public long getChatting_no() {
		return chatting_no;
	}
	public void setChatting_no(long chatting_no) {
		this.chatting_no = chatting_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getChatting_message() {
		return chatting_message;
	}
	public void setChatting_message(String chatting_message) {
		this.chatting_message = chatting_message;
	}
	public String getChatting_date() {
		return chatting_date;
	}
	public void setChatting_date(String chatting_date) {
		this.chatting_date = chatting_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
