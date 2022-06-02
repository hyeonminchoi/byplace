package com.byplace.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.byplace.dto.NoticeDTO;



public class NoticeDAO extends AbstractDAO {

	public void noticeWrite(NoticeDTO dto) {
		con = getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO notice (notice_title, notice_comment, notice_orifilename, notice_filename, user_no) "
				+ "VALUES (?, ?, ?, ?, (SELECT user_no FROM user WHERE user_id=?))";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_comment());
			pstmt.setString(3, dto.getNotice_orifilename());
			pstmt.setString(4, dto.getNotice_filename());
			pstmt.setString(5, dto.getUser_id());

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, null);
		}
	}

	void close(PreparedStatement pstmt, ResultSet rs) {
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

	public List<NoticeDTO> noticeList(int pageNo) {
		List<NoticeDTO> list = new ArrayList<NoticeDTO>();
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice LIMIT ?, 10";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_comment(rs.getString("notice_comment"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setNotice_orifilename(rs.getString("notice_orifilename"));
				dto.setNotice_filename(rs.getString("notice_filename"));
				dto.setTotalcount(rs.getInt("totalcount"));
				dto.setUser_no(rs.getLong("user_no"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}

		return list;
	}

	public NoticeDTO detail(int notice_no) {
		NoticeDTO dto = new NoticeDTO();
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice WHERE notice_no=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setUser_no(rs.getLong("user_no"));
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_comment(rs.getString("notice_comment"));
				dto.setNotice_date(rs.getString("notice_date"));
				dto.setNotice_orifilename(rs.getString("notice_orifilename"));
				dto.setNotice_filename(rs.getString("notice_filename"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, rs);
		}
		return dto;
	}
}
