package com.byplace.dto;

public class FoodDTO {
	long food_no, restaurant_no;
	String food_name, food_image, food_joined, food_description, user_id;
	int food_price, food_del;

	public long getFood_no() {
		return food_no;
	}

	public void setFood_no(long food_no) {
		this.food_no = food_no;
	}

	public long getRestaurant_no() {
		return restaurant_no;
	}

	public void setRestaurant_no(long restaurant_no) {
		this.restaurant_no = restaurant_no;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getFood_image() {
		return food_image;
	}

	public void setFood_image(String food_image) {
		this.food_image = food_image;
	}

	public String getFood_joined() {
		return food_joined;
	}

	public void setFood_joined(String food_joined) {
		this.food_joined = food_joined;
	}

	public String getFood_description() {
		return food_description;
	}

	public void setFood_description(String food_description) {
		this.food_description = food_description;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getFood_price() {
		return food_price;
	}

	public void setFood_price(int food_price) {
		this.food_price = food_price;
	}

	public int getFood_del() {
		return food_del;
	}

	public void setFood_del(int food_del) {
		this.food_del = food_del;
	}
	

}
