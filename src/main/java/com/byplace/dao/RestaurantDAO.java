package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.byplace.db.DBConnection;
import com.byplace.dto.CategoryDTO;
import com.byplace.dto.RestaurantDTO;

public class RestaurantDAO {

	public RestaurantDTO restboard(RestaurantDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO restaurant(restaurant_name, restaurant_description, restaurant_postcode, restaurant_roadAddress, restaurant_detailAddress, restaurant_extraAddress, category_category, restaurant_image, user_no) VALUES(?, ?, ?, ?, ?, ?, ?, ?, (SELECT user_no FROM user WHERE user_id=?)) ";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRestaurant_name());
			pstmt.setString(2, dto.getRestaurant_description());
			pstmt.setString(3, dto.getRestaurant_postcode());
			pstmt.setString(4, dto.getRestaurant_roadAddress());
			pstmt.setString(5, dto.getRestaurant_detailAddress());
			pstmt.setString(6, dto.getRestaurant_extraAddress());
			pstmt.setString(7, dto.getCategory_category());
			pstmt.setString(8, dto.getRestaurant_image());
			pstmt.setString(9, dto.getUser_id());
			
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

	public List<RestaurantDTO> rList() {
		List<RestaurantDTO> reslist = new ArrayList<RestaurantDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM restaurant";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RestaurantDTO dto = new RestaurantDTO();
				dto.setRestaurant_no(rs.getLong("restaurant_no"));
				dto.setRestaurant_name(rs.getString("restaurant_name"));
				dto.setRestaurant_description(rs.getString("restaurant_description"));
				dto.setRestaurant_roadAddress(rs.getString("restaurant_roadAddress"));
				dto.setRestaurant_detailAddress(rs.getString("restaurant_detailAddress"));
				dto.setRestaurant_extraAddress(rs.getString("restaurant_extraAddress"));
				dto.setRestaurant_image(rs.getString("restaurant_image"));
				reslist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reslist;
	}

	public RestaurantDTO resdetail(int restaurant_no) {
		RestaurantDTO dto = new RestaurantDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM restaurant WHERE restaurant_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, restaurant_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setRestaurant_name(rs.getString("restaurant_name"));
				dto.setRestaurant_image(rs.getString("restaurant_image"));
				dto.setRestaurant_description(rs.getString("restaurant_description"));
				dto.setRestaurant_postcode(rs.getString("restaurant_postcode"));
				dto.setRestaurant_roadAddress(rs.getString("restaurant_roadAddress"));
				dto.setRestaurant_detailAddress(rs.getString("restaurant_detailAddress"));
				dto.setRestaurant_extraAddress(rs.getString("restaurant_extraAddress"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public List<CategoryDTO> categorylist() {
		List<CategoryDTO> catelist = new ArrayList<CategoryDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM category";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCategory_category(rs.getString("category_category"));
				catelist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt !=null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return catelist;
	}
	
}
