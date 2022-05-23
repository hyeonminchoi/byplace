package com.byplace.dto;

public class RestaurantinfoDTO {
	long restaurantinfo_no, restaurant_no;
	String restaurantinfo_businessnumber, restaurantinfo_openinghours, restaurantinfo_descrpition;
	
	public long getRestaurantinfo_no() {
		return restaurantinfo_no;
	}
	public void setRestaurantinfo_no(long restaurantinfo_no) {
		this.restaurantinfo_no = restaurantinfo_no;
	}
	public long getRestaurant_no() {
		return restaurant_no;
	}
	public void setRestaurant_no(long restaurant_no) {
		this.restaurant_no = restaurant_no;
	}
	public String getRestaurantinfo_businessnumber() {
		return restaurantinfo_businessnumber;
	}
	public void setRestaurantinfo_businessnumber(String restaurantinfo_businessnumber) {
		this.restaurantinfo_businessnumber = restaurantinfo_businessnumber;
	}
	public String getRestaurantinfo_openinghours() {
		return restaurantinfo_openinghours;
	}
	public void setRestaurantinfo_openinghours(String restaurantinfo_openinghours) {
		this.restaurantinfo_openinghours = restaurantinfo_openinghours;
	}
	public String getRestaurantinfo_descrpition() {
		return restaurantinfo_descrpition;
	}
	public void setRestaurantinfo_descrpition(String restaurantinfo_descrpition) {
		this.restaurantinfo_descrpition = restaurantinfo_descrpition;
	}
}
