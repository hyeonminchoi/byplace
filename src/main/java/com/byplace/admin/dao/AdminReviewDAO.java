package com.byplace.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.byplace.admin.util.foodSearchColumn;
import com.byplace.admin.util.reviewSearchColumn;
import com.byplace.db.DBConnection;
import com.byplace.dto.ReviewDTO;

public class AdminReviewDAO {
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
		if(EnumUtils.isValidEnumIgnoreCase(reviewSearchColumn.class, searchColumn))
				sql = "SELECT COUNT(*) FROM reviewview WHERE restaurant_no = ? AND " + searchColumn + " LIKE ?";
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

	public List<ReviewDTO> findReviewList(String sort, String searchColumn, String searchValue, int currentPage, int pageSize, long restaurant_no) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(reviewSearchColumn.class, searchColumn))
				sql = "SELECT * FROM reviewview WHERE restaurant_no = ? AND " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<ReviewDTO> list = new ArrayList<>();
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
				ReviewDTO reviewDTO = new ReviewDTO();
				reviewDTO.setReview_no(rs.getLong("review_no"));
				reviewDTO.setRestaurant_no(rs.getLong("restaurant_no"));
				reviewDTO.setUser_no(rs.getLong("review_no"));
				reviewDTO.setUser_id(rs.getString("user_id"));
				reviewDTO.setReview_comment(rs.getString("review_comment"));
				reviewDTO.setReview_date(rs.getString("review_date"));
				reviewDTO.setReview_rating(rs.getDouble("review_rating"));
				reviewDTO.setReview_del(rs.getInt("review_del"));
				list.add(reviewDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}
	
	public int delete(long review_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE review SET review_del = 1 WHERE review_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, review_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
	
	public int accept(long review_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE review SET review_del = 0 WHERE review_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, review_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
}
