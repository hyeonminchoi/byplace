package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.byplace.db.DBConnection;
import com.byplace.dto.BoardDTO;
import com.byplace.dto.BoardcommentDTO;

public class BoardDAO {
	public List<BoardDTO> boardList(int pageNo) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board LIMIT ?, 10";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getLong("board_no"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_comment(rs.getString("board_comment"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_del(rs.getInt("board_del"));
				dto.setTotalcount(rs.getInt("board_count"));
//				dto.setTotalcount(rs.getInt("totalcount"));
//				dto.setUser_id(rs.getString("user_id"));
//				dto.setUser_no(rs.getLong("user_no"));
//				dto.setUser_status(rs.getInt("user_status"));
				list.add(dto);// 붙여넣기
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

		return list;
	}

	public void boardwrite(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board (board_title, board_comment, user_no) "
				+ "VALUES (?, ?, (SELECT user_no FROM user WHERE user_id=?))";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_comment());
			pstmt.setLong(3, dto.getUser_no());
			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void countUp(long board_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_count=board_count + 1 WHERE board_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
	}

	public BoardDTO detail(long board_no) {
		BoardDTO dto = new BoardDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM may_boardview WHERE b_no=?";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setBoard_no(rs.getLong("board_no"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_comment(rs.getString("board_comment"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_del(rs.getInt("board_del"));
				dto.setTotalcount(rs.getInt("board_count"));
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

	public List<BoardcommentDTO> commentList(long board_no) {
		List<BoardcommentDTO> list = new ArrayList<BoardcommentDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM may_commentview WHERE b_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				BoardcommentDTO dto = new BoardcommentDTO();
				dto.setBoardcomment_no(rs.getLong("boardcomment_no"));
				dto.setUser_no(rs.getLong("user_no"));
				dto.setBoardcomment_comment(rs.getString("boardcomment_comment"));
				dto.setBoardcomment_date(rs.getString("boardcomment_date"));
				dto.setBoardcomment_title(rs.getString("boardcomment_title"));
				dto.setBoardcomment_del(rs.getInt("boardcomment_del"));
				dto.setUser_id(rs.getString("user_id"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}


	
}