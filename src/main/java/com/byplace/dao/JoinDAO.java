package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.byplace.db.DBConnection;
import com.byplace.dto.UserDTO;

public class JoinDAO {
	public int join(UserDTO dto) {
		//가입 여부를 int로 확인해보겠습니다.
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO user"
				+ "(user_id, user_password, user_name, user_nickname, user_email, "
				+ "user_postcode, user_roadAddress, user_detailAddress, user_extraAddress, "
				+ "user_birthday, user_type, user_phone) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
	         pstmt.setString(2, dto.getUser_password());
	         pstmt.setString(3, dto.getUser_name());
	         pstmt.setString(4, dto.getUser_nickname());
	         pstmt.setString(5, dto.getUser_email());
	         pstmt.setString(6, dto.getUser_postcode());
	         pstmt.setString(7, dto.getUser_roadAddress());
	         pstmt.setString(8, dto.getUser_detailAddress());
	         pstmt.setString(9, dto.getUser_extraAddress());
	         pstmt.setString(10, dto.getUser_birthday());
	         pstmt.setString(11, dto.getUser_type());
	         pstmt.setString(12, dto.getUser_phone());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

//	public int idCheck(String id) {
//		Connection con =null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "SELECT COUNT(*) FROM may_member WHERE m_id=?";
//		int result = 1;//이미 등록된 ID
//		
//		try {
//			con = DBConnection.dbConn();
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				result = rs.getInt(1);
//				//System.out.println("결과는:"+result+"개");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
}
