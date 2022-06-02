package com.byplace.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.byplace.admin.util.reportboardSearchColumn;
import com.byplace.db.DBConnection;
import com.byplace.dto.ReportboardDTO;

public class AdminReportboardDAO {
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

	// 신고게시판 글 읽기
	public List<ReportboardDTO> findAll(String sort, String searchColumn, String searchValue, int currentPage, int pageSize) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(reportboardSearchColumn.class, searchColumn))
				sql = "SELECT * FROM adminreportboardview WHERE " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<ReportboardDTO> list = new ArrayList<>();
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
				ReportboardDTO reportboardDTO = new ReportboardDTO();
				reportboardDTO.setReportboard_no(rs.getLong("reportboard_no"));
				reportboardDTO.setReportboard_title(rs.getString("reportboard_title"));
				reportboardDTO.setReportboard_comment(rs.getString("reportboard_comment"));
				reportboardDTO.setReportboard_date(rs.getString("reportboard_date"));
				reportboardDTO.setReportboard_del(rs.getInt("reportboard_del"));
				reportboardDTO.setReportboard_count(rs.getInt("reportboard_count"));
				reportboardDTO.setReportboard_commentcount(rs.getInt("reportboard_commentcount"));
				reportboardDTO.setUser_id(rs.getString("user_id"));
				reportboardDTO.setUser_no(rs.getLong("user_no"));
				list.add(reportboardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}

	// 글 삭제
	public int delete(long reportboard_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboard SET reportboard_del = 1 WHERE reportboard_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboard_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	public int edit(ReportboardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboard SET reportboard_title=?, reportboard_comment=? WHERE reportboard_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getReportboard_title());
			pstmt.setString(2, dto.getReportboard_comment());
			pstmt.setLong(3, dto.getReportboard_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	public int accept(long reportboard_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboard SET reportboard_del = 0 WHERE reportboard_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboard_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	public int count(String searchColumn, String searchValue) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(reportboardSearchColumn.class, searchColumn))
				sql = "SELECT COUNT(*) FROM adminreportboardview WHERE " + searchColumn + " LIKE ?";
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
	
	public int countRecovery(String searchColumn, String searchValue) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(reportboardSearchColumn.class, searchColumn))
				sql = "SELECT COUNT(*) FROM reportboard WHERE reportboard_del = 1 AND " + searchColumn + " LIKE ?";
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
	
	// 글 차단
	public int block(long reportboard_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboard SET reportboard_del = -1 WHERE reportboard_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboard_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
	
	// 차단, 삭제 게시물 읽기
	public List<ReportboardDTO> findRecovery(String sort, String searchColumn, String searchValue, int currentPage, int pageSize) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(reportboardSearchColumn.class, searchColumn))
				sql = "SELECT * FROM adminreportboardview WHERE reportboard_del IN (-1, 1) AND " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<ReportboardDTO> list = new ArrayList<>();
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
				ReportboardDTO reportboardDTO = new ReportboardDTO();
				reportboardDTO.setReportboard_no(rs.getLong("reportboard_no"));
				reportboardDTO.setReportboard_title(rs.getString("reportboard_title"));
				reportboardDTO.setReportboard_comment(rs.getString("reportboard_comment"));
				reportboardDTO.setReportboard_date(rs.getString("reportboard_date"));
				reportboardDTO.setReportboard_del(rs.getInt("reportboard_del"));
				reportboardDTO.setReportboard_count(rs.getInt("reportboard_count"));
				reportboardDTO.setReportboard_commentcount(rs.getInt("reportboard_commentcount"));
				reportboardDTO.setUser_id(rs.getString("user_id"));
				reportboardDTO.setUser_no(rs.getLong("user_no"));
				list.add(reportboardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}
	
	// 글 제거
	public int drop(long reportboard_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM reportboard WHERE reportboard_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboard_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
}
