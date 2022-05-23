package com.byplace.dto;

public class ReviewDTO {
	long review_no, restaurant_no, user_no;
	String review_comment, review_date, review_image;
	double review_rating;
	int review_del;
	
	String user_id;

	public long getReview_no() {
		return review_no;
	}

	public void setReview_no(long review_no) {
		this.review_no = review_no;
	}

	public long getRestaurant_no() {
		return restaurant_no;
	}

	public void setRestaurant_no(long restaurant_no) {
		this.restaurant_no = restaurant_no;
	}

	public long getUser_no() {
		return user_no;
	}

	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}

	public String getReview_comment() {
		return review_comment;
	}

	public void setReview_comment(String review_comment) {
		this.review_comment = review_comment;
	}

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

	public String getReview_image() {
		return review_image;
	}

	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}

	public double getReview_rating() {
		return review_rating;
	}

	public void setReview_rating(double review_rating) {
		this.review_rating = review_rating;
	}

	public int getReview_del() {
		return review_del;
	}

	public void setReview_del(int review_del) {
		this.review_del = review_del;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
