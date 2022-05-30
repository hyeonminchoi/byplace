package com.byplace.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.byplace.admin.util.boardSearchColumn;
import com.byplace.db.DBConnection;
import com.byplace.dto.BoardDTO;

public class AdminBoardDAO {
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

	// 자유게시판 글 읽기
	public List<BoardDTO> findAll(String sort, String searchColumn, String searchValue, int currentPage, int pageSize) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(boardSearchColumn.class, searchColumn))
				sql = "SELECT * FROM adminboardview WHERE " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<BoardDTO> list = new ArrayList<>();
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
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setBoard_no(rs.getLong("board_no"));
				boardDTO.setBoard_title(rs.getString("board_title"));
				boardDTO.setBoard_comment(rs.getString("board_comment"));
				boardDTO.setBoard_date(rs.getString("board_date"));
				boardDTO.setBoard_del(rs.getInt("board_del"));
				boardDTO.setBoard_count(rs.getInt("board_count"));
				boardDTO.setUser_id(rs.getString("user_id"));
				boardDTO.setUser_no(rs.getLong("user_no"));
				list.add(boardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}

	// 글 삭제
	public int delete(long board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del = 1 WHERE board_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	public int edit(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title=?, board_comment=? WHERE board_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_comment());
			pstmt.setLong(3, dto.getBoard_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	public int accept(long board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del = 0 WHERE board_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
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
		if(EnumUtils.isValidEnumIgnoreCase(boardSearchColumn.class, searchColumn))
				sql = "SELECT COUNT(*) FROM adminboardview WHERE " + searchColumn + " LIKE ?";
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
		if(EnumUtils.isValidEnumIgnoreCase(boardSearchColumn.class, searchColumn))
				sql = "SELECT COUNT(*) FROM board WHERE board_del = 1 AND " + searchColumn + " LIKE ?";
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
	public int block(long board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del = -1 WHERE board_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
	
	// 차단, 삭제 게시물 읽기
	public List<BoardDTO> findRecovery(String sort, String searchColumn, String searchValue, int currentPage, int pageSize) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(boardSearchColumn.class, searchColumn))
				sql = "SELECT * FROM adminboardview WHERE board_del IN (-1, 1) AND " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<BoardDTO> list = new ArrayList<>();
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
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setBoard_no(rs.getLong("board_no"));
				boardDTO.setBoard_title(rs.getString("board_title"));
				boardDTO.setBoard_comment(rs.getString("board_comment"));
				boardDTO.setBoard_date(rs.getString("board_date"));
				boardDTO.setBoard_del(rs.getInt("board_del"));
				boardDTO.setBoard_count(rs.getInt("board_count"));
				boardDTO.setUser_id(rs.getString("user_id"));
				boardDTO.setUser_no(rs.getLong("user_no"));
				list.add(boardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}
	
	// 글 제거
	public int drop(long board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM board WHERE board_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}
}
