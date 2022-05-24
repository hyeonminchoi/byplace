package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.byplace.db.DBConnection;
import com.byplace.dto.RestaurantDTO;

public class RestaurantDAO {

	public RestaurantDTO restboard(RestaurantDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO restaurant(restaurant_name, restaurant_description, restaurant_postcode, restaurant_roadAddress, restaurant_detailAddress, restaurant_extraAddress, category_category, restaurant_image) VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRestaurant_name());
			pstmt.setString(2, dto.getRestaurant_description());
			pstmt.setString(3, dto.getRestaurant_postcode());
			pstmt.setString(4, dto.getRestaurant_roadAddress());
			pstmt.setString(5, dto.getRestaurant_detailAddress());
			pstmt.setString(6, dto.getRestaurant_restaurant_extraAddress());
			pstmt.setString(7, dto.getCategory_category());
			pstmt.setString(8, dto.getRestaurant_image());
			
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
}
