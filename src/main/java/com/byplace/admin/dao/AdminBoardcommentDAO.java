package com.byplace.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.byplace.admin.util.foodSearchColumn;
import com.byplace.admin.util.boardcommentSearchColumn;
import com.byplace.db.DBConnection;
import com.byplace.dto.BoardcommentDTO;

public class AdminBoardcommentDAO {
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
	
	public int count(String searchColumn, String searchValue, long board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(boardcommentSearchColumn.class, searchColumn))
				sql = "SELECT COUNT(*) FROM adminboardcommentview WHERE board_no = ? AND " + searchColumn + " LIKE ?";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
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

	public List<BoardcommentDTO> findBoardcommentList(String sort, String searchColumn, String searchValue, int currentPage, int pageSize, long board_no) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(boardcommentSearchColumn.class, searchColumn))
				sql = "SELECT * FROM adminboardcommentview WHERE board_no = ? AND " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<BoardcommentDTO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			pstmt.setString(2, "%" + searchValue + "%");
			pstmt.setInt(3, (currentPage - 1) * pageSize);
			pstmt.setInt(4, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardcommentDTO boardcommentDTO = new BoardcommentDTO();
				boardcommentDTO.setBoardcomment_no(rs.getLong("boardcomment_no"));
				boardcommentDTO.setBoard_no(rs.getLong("board_no"));
				boardcommentDTO.setUser_no(rs.getLong("user_no"));
				boardcommentDTO.setUser_id(rs.getString("user_id"));
				boardcommentDTO.setBoardcomment_comment(rs.getString("boardcomment_comment"));
				boardcommentDTO.setBoardcomment_date(rs.getString("boardcomment_date"));
				boardcommentDTO.setBoardcomment_del(rs.getInt("boardcomment_del"));
				list.add(boardcommentDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}
	
	public int delete(long boardcomment_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE boardcomment SET boardcomment_del = 1 WHERE boardcomment_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, boardcomment_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
	
	public int accept(long boardcomment_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE boardcomment SET boardcomment_del = 0 WHERE boardcomment_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, boardcomment_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
}
