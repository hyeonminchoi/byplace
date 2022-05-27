package com.byplace.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.byplace.db.DBConnection;
import com.byplace.dto.RestaurantDTO;
import com.byplace.dto.UserDTO;

public class AdminRestaurantDAO {
	public void close(ResultSet rs, PreparedStatement pstmt) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//전체 음식점 정보 읽어오기
	public List<RestaurantDTO> findAll(String sort, int currentPage, int pageSize){
		String sql = "SELECT * FROM restaurantView ORDER BY " + sort + " LIMIT ?, ?";
		List<RestaurantDTO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (currentPage - 1) * pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RestaurantDTO restaurantDTO = new RestaurantDTO();
				restaurantDTO.setRestaurant_no(rs.getLong("restaurant_no"));
				restaurantDTO.setRestaurant_name(rs.getString("restaurant_name"));
				restaurantDTO.setRestaurant_description(rs.getString("restaurant_description"));
				restaurantDTO.setRestaurant_postcode(rs.getString("restaurant_postcode"));
				restaurantDTO.setRestaurant_roadAddress(rs.getString("restaurant_roadAddress"));
				restaurantDTO.setRestaurant_detailAddress(rs.getString("restaurant_detailAddress"));
				restaurantDTO.setRestaurant_extraAddress(rs.getString("restaurant_extraAddress"));
				restaurantDTO.setCategory_category(rs.getString("category_category"));
				restaurantDTO.setRestaurant_image(rs.getString("restaurant_image"));
				restaurantDTO.setRestaurant_joined(rs.getString("restaurant_joined"));
				restaurantDTO.setUser_no(rs.getLong("user_no"));
				restaurantDTO.setRestaurant_del(rs.getInt("restaurant_del"));
				restaurantDTO.setUser_id(rs.getString("user_id"));
				list.add(restaurantDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}
}
