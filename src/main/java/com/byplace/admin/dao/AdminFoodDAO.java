package com.byplace.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.byplace.admin.util.foodSearchColumn;
import com.byplace.db.DBConnection;
import com.byplace.dto.FoodDTO;
import com.byplace.dto.FoodDTO;

public class AdminFoodDAO {
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
	
	public int count(String searchColumn, String searchValue, long restaurant_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(foodSearchColumn.class, searchColumn))
				sql = "SELECT COUNT(*) FROM food WHERE restaurant_no = ? AND " + searchColumn + " LIKE ?";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, restaurant_no);
			pstmt.setString(2, "%" + searchValue + "%");
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return 0;
	}

	public List<FoodDTO> findFoodList(String sort, String searchColumn, String searchValue, int currentPage, int pageSize, long restaurant_no) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(foodSearchColumn.class, searchColumn))
				sql = "SELECT * FROM food WHERE restaurant_no = ? AND " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<FoodDTO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, restaurant_no);
			pstmt.setString(2, "%" + searchValue + "%");
			pstmt.setInt(3, (currentPage - 1) * pageSize);
			pstmt.setInt(4, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FoodDTO foodDTO = new FoodDTO();
				foodDTO.setFood_no(rs.getLong("food_no"));
				foodDTO.setFood_name(rs.getString("food_name"));
				foodDTO.setFood_price(rs.getInt("food_price"));
				foodDTO.setRestaurant_no(rs.getLong("restaurant_no"));
				foodDTO.setFood_image(rs.getString("food_image"));
				foodDTO.setFood_joined(rs.getString("food_joined"));
				foodDTO.setFood_description(rs.getString("food_description"));
				foodDTO.setFood_del(rs.getInt("food_del"));
				list.add(foodDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}
	
	public int delete(long food_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE food SET food_del = 1 WHERE food_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, food_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
	
	public int edit(FoodDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE food SET food_name=?, food_description=?, food_price=? WHERE food_no = ? AND restaurant_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getFood_name());
			pstmt.setString(2, dto.getFood_description());
			pstmt.setInt(3, dto.getFood_price());
			pstmt.setLong(4, dto.getFood_no());
			pstmt.setLong(5, dto.getRestaurant_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
	
	public int imageEdit(FoodDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE food SET food_image=? WHERE food_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getFood_image());
			pstmt.setLong(2, dto.getFood_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
	
	public int accept(long food_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE food SET food_del = 0 WHERE food_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, food_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
}
