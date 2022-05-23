package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.byplace.db.DBConnection;
import com.byplace.dto.UserDTO;

public class LoginDAO {
	// 사용자 정보 가져오는 메소드 userInfo
	public UserDTO userInfo(UserDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user WHERE user_id=?";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setUser_no(rs.getLong("user_no"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_password(rs.getString("user_password"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setUser_nickname(rs.getString("user_nickname"));
				dto.setUser_email(rs.getString("user_email"));
				dto.setUser_postcode(rs.getString("user_postcode"));
				dto.setUser_roadAddress(rs.getString("user_roadAddress"));
				dto.setUser_detailAddress(rs.getString("user_detailAddress"));
				dto.setUser_extraAddress(rs.getString("user_extraAddress"));
				dto.setUser_birthday(rs.getString("user_birthday"));
				dto.setUser_joined(rs.getString("user_joined"));
				dto.setUser_type(rs.getString("user_type"));
				dto.setUser_phone(rs.getString("user_phone"));
				dto.setUser_del(rs.getInt("user_del"));
				dto.setUser_status(rs.getInt("user_status"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
//
//	// 로그인
//	public UserDTO login(UserDTO dto) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		String sql = "SELECT count(*) as count, m_name FROM may_member WHERE m_id=? AND m_pw=?";
//		ResultSet rs = null;
//
//		try {
//			con = DBConnection.dbConn();
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, dto.getUser_id());
//			pstmt.setString(2, dto.getUser_pw());
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				dto.setCount(rs.getInt("count"));
//				dto.setUser_name(rs.getString("User_name"));
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				// if(con != null) {con.close();}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return dto;
//	}
}
