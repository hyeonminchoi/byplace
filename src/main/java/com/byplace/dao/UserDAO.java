package com.byplace.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.byplace.db.DBConnection;
import com.byplace.dto.UserDTO;

public class UserDAO {
	public int join(UserDTO dto) {
		// 가입 여부를 int로 확인해보겠습니다.
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		if(dto.getUser_type().equals("사업자"))
			sql = "INSERT INTO user" + "(user_id, user_password, user_name, user_nickname, user_email, "
				+ "user_postcode, user_roadAddress, user_detailAddress, user_extraAddress, "
				+ "user_birthday, user_type, user_phone) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		else
			sql = "INSERT INTO user" + "(user_id, user_password, user_name, user_nickname, user_email, "
					+ "user_postcode, user_roadAddress, user_detailAddress, user_extraAddress, "
					+ "user_birthday, user_type, user_phone, user_status) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, encrypt(dto.getUser_password()));
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

	public static String encrypt(String user_password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] passBytes = user_password.getBytes();
		md.reset();
		byte[] digested = md.digest(passBytes);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digested.length; i++)
			sb.append(Integer.toHexString(0xff & digested[i]));
		return sb.toString();
	}
	
	//아이디 중복확인

	public int idCheck(String id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM user WHERE user_id=?";
		int result = 1;//이미 등록된 ID
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	//닉네임 중복확인
	public int nicknameCheck(String nickname) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM user WHERE user_nickname=?";
		int result = 1;//이미 등록된 닉네임
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
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

	// 로그인
	public UserDTO login(UserDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM user WHERE user_id=? AND user_password=?";
		ResultSet rs = null;

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, encrypt(dto.getUser_password()));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if(rs.getInt("user_status")==1) {
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
				} else {
					return null;
				}
			} else {
				return null;
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
				// if(con != null) {con.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	////아이디 찾기
	
	public String findId(UserDTO dto) {
		Connection con;
		String user_id = null;

		try {
			con = DBConnection.dbConn();
			String sql = "SELECT user_id FROM user WHERE user_name=? and user_phone=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_name());
			pstmt.setString(2, dto.getUser_phone());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				user_id = rs.getString("user_id");// rs.getString(1)
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_id;
	}
	//비밀번호 초기화(사용자 번호 찾기)
	public long findpw(UserDTO dto) {
		Connection con;
		long result = -1;
		
		try {
			con=DBConnection.dbConn();
			String sql = "SELECT user_no FROM user WHERE user_id=? and user_name=? and user_phone=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_name());
			pstmt.setString(3, dto.getUser_phone());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getLong("user_no");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//비밀번호 변경
	public int changePw(long user_no, String password) {
		Connection con = null;
		int result = 0;
		
		try {
			con=DBConnection.dbConn();
			String sql = "UPDATE user SET user_password = ? WHERE user_no = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, encrypt(password));
			pstmt.setLong(2, user_no);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int passwordCheck(long user_no, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user WHERE user_no = ? AND user_password=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, user_no);
			pstmt.setString(2, encrypt(password));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int userfix(UserDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "UPDATE user SET user_name= ?,user_nickname=?,user_phone=?,"
				+ "user_email=?,user_postcode=?,user_roadAddress=?,"
				+ "user_detailAddress=?,user_extraAddress=? "
				+ "WHERE user_no = ?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_name());
			pstmt.setString(2, dto.getUser_nickname());
			pstmt.setString(3, dto.getUser_phone());
			pstmt.setString(4, dto.getUser_email());
			pstmt.setString(5, dto.getUser_postcode());
			pstmt.setString(6, dto.getUser_roadAddress());
			pstmt.setString(7, dto.getUser_detailAddress());
			pstmt.setString(8, dto.getUser_extraAddress());
			pstmt.setLong(9, dto.getUser_no());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void quit(Long user_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE user SET user_del = 1 WHERE user_no = ?";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, user_no);
			pstmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void userlog(long user_no, String status, String ip) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO userlog(user_no, userlog_status, userlog_ipaddr) VALUES(?, ?, ?)";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, user_no);
			pstmt.setString(2, status);
			pstmt.setString(3, ip);
			pstmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
