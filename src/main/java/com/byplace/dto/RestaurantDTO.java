package com.byplace.dto;

public class RestaurantDTO {
	long restaurant_no, user_no;
	String restaurant_name, restaurant_description, restaurant_postcode, restaurant_roadAddress,
	       restaurant_detailAddress, restaurant_extraAddress, category_category,
	       restaurant_image, restaurant_joined;
	int restaurant_del;
	
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
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getRestaurant_description() {
		return restaurant_description;
	}
	public void setRestaurant_description(String restaurant_description) {
		this.restaurant_description = restaurant_description;
	}
	public String getRestaurant_postcode() {
		return restaurant_postcode;
	}
	public void setRestaurant_postcode(String restaurant_postcode) {
		this.restaurant_postcode = restaurant_postcode;
	}
	public String getRestaurant_roadAddress() {
		return restaurant_roadAddress;
	}
	public void setRestaurant_roadAddress(String restaurant_roadAddress) {
		this.restaurant_roadAddress = restaurant_roadAddress;
	}
	public String getRestaurant_detailAddress() {
		return restaurant_detailAddress;
	}
	public void setRestaurant_detailAddress(String restaurant_detailAddress) {
		this.restaurant_detailAddress = restaurant_detailAddress;
	}
	public String getRestaurant_extraAddress() {
		return restaurant_extraAddress;
	}
	public void setRestaurant_extraAddress(String restaurant_extraAddress) {
		this.restaurant_extraAddress = restaurant_extraAddress;
	}
	public String getCategory_category() {
		return category_category;
	}
	public void setCategory_category(String category_category) {
		this.category_category = category_category;
	}
	public String getRestaurant_image() {
		return restaurant_image;
	}
	public void setRestaurant_image(String restaurant_image) {
		this.restaurant_image = restaurant_image;
	}
	public String getRestaurant_joined() {
		return restaurant_joined;
	}
	public void setRestaurant_joined(String restaurant_joined) {
		this.restaurant_joined = restaurant_joined;
	}
	public int getRestaurant_del() {
		return restaurant_del;
	}
	public void setRestaurant_del(int restaurant_del) {
		this.restaurant_del = restaurant_del;
	}
	
	
}
