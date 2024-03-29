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
		String sql = "SELECT * FROM boardview LIMIT ?, 10";

		try {
			con = DBConnection.dbConn(); //데이터베이스 연결
			pstmt = con.prepareStatement(sql); //sql문 실행
			pstmt.setInt(1, pageNo); //sql문 ? 자리 넣어주기
			rs = pstmt.executeQuery(); //리턴
			while (rs.next()) { //다음행으로 커서 이동
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getLong("board_no"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_comment(rs.getString("board_comment"));
				dto.setBoard_commentcount(rs.getInt("board_commentcount"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_del(rs.getInt("board_del"));
				dto.setBoard_count(rs.getInt("board_count"));
				dto.setTotalcount(rs.getInt("totalcount"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_no(rs.getLong("user_no"));
				dto.setUser_status(rs.getInt("user_status"));
				list.add(dto);// 붙여넣기
			}
		} catch (Exception e) {//무조건 예외처리
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
				+ "VALUES (?, ?, (SELECT user_no FROM user WHERE user_no=?))";

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
		String sql = "SELECT * FROM boardview WHERE board_no=?";

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
				dto.setBoard_count(rs.getInt("board_count"));
				dto.setUser_no(rs.getLong("user_no"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setBoard_commentcount(rs.getInt("board_commentcount"));
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
		String sql = "SELECT * FROM boardcommentview WHERE board_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, board_no);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				BoardcommentDTO dto = new BoardcommentDTO();
				dto.setBoardcomment_no(rs.getLong("boardcomment_no"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoardcomment_comment(rs.getString("boardcomment_comment"));
				dto.setUser_no(rs.getLong("user_no"));
				dto.setBoardcomment_date(rs.getString("boardcomment_date"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_name(rs.getString("user_name"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public void update(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title=?, board_comment=? " + "WHERE board_no=? AND user_no=("
				+ "SELECT user_no FROM user WHERE user_id=?)";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_comment());
			pstmt.setLong(3, dto.getBoard_no());
			pstmt.setString(4, dto.getUser_id());

			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public BoardcommentDTO commentDetail(BoardcommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boardcommentview WHERE boardcomment_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getBoardcomment_no());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBoardcomment_comment(rs.getString("boardcomment_comment"));
				dto.setBoardcomment_date(rs.getString("boardcomment_date"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_name(rs.getString("user_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public void commentUpdate(BoardcommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE boardcomment SET boardcomment_comment=? WHERE boardcomment_no=? "
				+ "AND user_no=(SELECT user_no FROM user WHERE user_id=?)";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoardcomment_comment());
			pstmt.setLong(2, dto.getBoardcomment_no());
			pstmt.setString(3, dto.getUser_id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void cdel(BoardcommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE boardcomment SET boardcomment_del=1 "
				+ "WHERE boardcomment_no=? AND user_no="
				+ "(SELECT user_no FROM user WHERE user_id=?)";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getBoardcomment_no());//물음표에  
			pstmt.setString(2, dto.getUser_id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void postDel(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del=1 " + "WHERE board_no=? AND user_no="
				+ "(SELECT user_no FROM user WHERE user_id=?)";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getBoard_no());
			pstmt.setString(2, dto.getUser_id());
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

	public void commentWrite(BoardcommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO boardcomment (board_no, boardcomment_comment, user_no) "
				+ "VALUES (?, ?, (SELECT user_no FROM user WHERE user_id=?))";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getBoard_no());
			pstmt.setString(2, dto.getBoardcomment_comment());
			pstmt.setString(3, dto.getUser_id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	
}