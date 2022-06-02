package com.byplace.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.byplace.admin.util.foodSearchColumn;
import com.byplace.admin.util.reportboardcommentSearchColumn;
import com.byplace.db.DBConnection;
import com.byplace.dto.ReportboardcommentDTO;

public class AdminReportboardcommentDAO {
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
	
	public int count(String searchColumn, String searchValue, long reportboard_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(reportboardcommentSearchColumn.class, searchColumn))
				sql = "SELECT COUNT(*) FROM adminreportboardcommentview WHERE reportboard_no = ? AND " + searchColumn + " LIKE ?";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboard_no);
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

	public List<ReportboardcommentDTO> findReportboardcommentList(String sort, String searchColumn, String searchValue, int currentPage, int pageSize, long reportboard_no) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(reportboardcommentSearchColumn.class, searchColumn))
				sql = "SELECT * FROM adminreportboardcommentview WHERE reportboard_no = ? AND " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<ReportboardcommentDTO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboard_no);
			pstmt.setString(2, "%" + searchValue + "%");
			pstmt.setInt(3, (currentPage - 1) * pageSize);
			pstmt.setInt(4, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportboardcommentDTO reportboardcommentDTO = new ReportboardcommentDTO();
				reportboardcommentDTO.setReportboardcomment_no(rs.getLong("reportboardcomment_no"));
				reportboardcommentDTO.setReportboard_no(rs.getLong("reportboard_no"));
				reportboardcommentDTO.setUser_no(rs.getLong("user_no"));
				reportboardcommentDTO.setUser_id(rs.getString("user_id"));
				reportboardcommentDTO.setReportboardcomment_comment(rs.getString("reportboardcomment_comment"));
				reportboardcommentDTO.setReportboardcomment_date(rs.getString("reportboardcomment_date"));
				reportboardcommentDTO.setReportboardcomment_del(rs.getInt("reportboardcomment_del"));
				list.add(reportboardcommentDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}
	
	public int delete(long reportboardcomment_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboardcomment SET reportboardcomment_del = 1 WHERE reportboardcomment_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboardcomment_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
	
	public int accept(long reportboardcomment_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboardcomment SET reportboardcomment_del = 0 WHERE reportboardcomment_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboardcomment_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
}
