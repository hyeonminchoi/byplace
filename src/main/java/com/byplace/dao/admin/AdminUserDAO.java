package com.byplace.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.byplace.db.DBConnection;
import com.byplace.dto.UserDTO;

public class AdminUserDAO {
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

	//유저 리스트 읽어오기
	public List<UserDTO> findAll(String sort, int currentPage, int pageSize) {
		String sql = "SELECT * FROM user ORDER BY " + sort + " LIMIT ?, ?";
		List<UserDTO> list = new ArrayList<>();
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
				UserDTO userDTO = new UserDTO();
				userDTO.setUser_no(rs.getLong("user_no"));
				userDTO.setUser_id(rs.getString("user_id"));
				userDTO.setUser_name(rs.getString("user_name"));
				userDTO.setUser_nickname(rs.getString("user_nickname"));
				userDTO.setUser_email(rs.getString("user_email"));
				userDTO.setUser_postcode(rs.getString("user_postcode"));
				userDTO.setUser_roadAddress(rs.getString("user_roadAddress"));
				userDTO.setUser_detailAddress(rs.getString("user_detailAddress"));
				userDTO.setUser_extraAddress(rs.getString("user_extraAddress"));
				userDTO.setUser_birthday(rs.getString("user_birthday"));
				userDTO.setUser_joined(rs.getString("user_joined"));
				userDTO.setUser_type(rs.getString("user_type"));
				userDTO.setUser_phone(rs.getString("user_phone"));
				userDTO.setUser_status(rs.getInt("user_status"));
				userDTO.setUser_del(rs.getInt("user_del"));
				list.add(userDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}

	//user 테이블 갯수 구하기
	public int count() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM user";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
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


	//탈퇴
	public int delete(int user_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE user SET user_del = 1 WHERE user_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	/*
	 * //사용자 수정 public int edit(int category_no, String category_category) {
	 * Connection con = null; PreparedStatement pstmt = null; String sql =
	 * "UPDATE category SET category_category = ? WHERE category_no = ?"; int result
	 * = 0; try { con = DBConnection.dbConn(); pstmt = con.prepareStatement(sql);
	 * pstmt.setString(1, category_category); pstmt.setInt(2, category_no); result =
	 * pstmt.executeUpdate(); } catch(Exception e) { } finally { close(null, pstmt);
	 * } return result; }
	 */

}
