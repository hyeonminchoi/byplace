package com.byplace.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.byplace.admin.util.noticeSearchColumn;
import com.byplace.db.DBConnection;
import com.byplace.dto.NoticeDTO;
import com.byplace.dto.NoticeDTO;

public class AdminNoticeDAO {
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

	// 모든 공지사항 읽기
	public List<NoticeDTO> findAll(String sort, String searchColumn, String searchValue, int currentPage, int pageSize) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(noticeSearchColumn.class, searchColumn))
				sql = "SELECT * FROM notice WHERE " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<NoticeDTO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchValue + "%");
			pstmt.setInt(2, (currentPage - 1) * pageSize);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeDTO noticeDTO = new NoticeDTO();
				noticeDTO.setNotice_no(rs.getLong("notice_no"));
				noticeDTO.setNotice_title(rs.getString("notice_title"));
				noticeDTO.setNotice_comment(rs.getString("notice_comment"));
				noticeDTO.setNotice_date(rs.getString("notice_date"));
				noticeDTO.setNotice_del(rs.getInt("notice_del"));
				noticeDTO.setNotice_filename(rs.getString("notice_filename"));
				noticeDTO.setNotice_orifilename(rs.getString("notice_orifilename"));
				noticeDTO.setUser_no(rs.getLong("user_no"));
				list.add(noticeDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}

	// 공지사항 삭제
	public int delete(long notice_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE notice SET notice_del = 1 WHERE notice_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, notice_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	//공지사항 수정
	public int edit(NoticeDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE notice SET notice_title=?, notice_comment=? WHERE notice_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_comment());
			pstmt.setLong(3, dto.getNotice_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	//공지사항 게시
	public int accept(long notice_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String string = "UPDATE notice SET notice_del = 0 WHERE notice_no=?";
		String sql = string;
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, notice_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	//공지사항 갯수
	public int count(String searchColumn, String searchValue) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(noticeSearchColumn.class, searchColumn))
				sql = "SELECT COUNT(*) FROM notice WHERE " + searchColumn + " LIKE ?";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchValue + "%");
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
	
	//이미지 수정
	public int imageEdit(NoticeDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE notice SET notice_filename=?, notice_orifilename=? WHERE notice_no=?";
		
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getNotice_filename());
			pstmt.setString(2, dto.getNotice_orifilename());
			pstmt.setLong(3, dto.getNotice_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
}
