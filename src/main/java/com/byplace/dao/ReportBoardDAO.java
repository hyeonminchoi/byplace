package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.byplace.db.DBConnection;

import com.byplace.dto.ReportboardDTO;
import com.byplace.dto.ReportboardcommentDTO;






public class ReportBoardDAO {
	public List<ReportboardDTO> ReportboardList(int pageNo) {
		List<ReportboardDTO> list = new ArrayList<ReportboardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reportboardview LIMIT ?, 10";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportboardDTO dto = new ReportboardDTO();
				dto.setReportboard_no(rs.getLong("reportboard_no"));
				dto.setReportboard_title(rs.getString("reportboard_title"));
				dto.setReportboard_comment(rs.getString("reportboard_comment"));
				dto.setReportboard_commentcount(rs.getInt("reportboard_commentcount"));
				dto.setReportboard_date(rs.getString("reportboard_date"));
				dto.setReportboard_del(rs.getInt("reportboard_del"));
				dto.setReportboard_count(rs.getInt("reportboard_count"));
				dto.setTotalcount(rs.getInt("totalcount"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_no(rs.getLong("user_no"));
				dto.setUser_status(rs.getInt("user_status"));
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

	public void Reportboardwrite(ReportboardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO reportboard (reportboard_title, reportboard_comment, user_no) "
				+ "VALUES (?, ?, (SELECT user_no FROM user WHERE user_no=?))";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getReportboard_title());
			pstmt.setString(2, dto.getReportboard_comment());
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

	public void ReportcountUp(long reportboard_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboard SET reportboard_count=reportboard_count + 1 WHERE reportboard_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboard_no);
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

	public ReportboardDTO Reportdetail(long reportboard_no) {
		ReportboardDTO dto = new ReportboardDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reportboardview WHERE reportboard_no=?";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboard_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setReportboard_no(rs.getLong("reportboard_no"));
				dto.setReportboard_title(rs.getString("reportboard_title"));
				dto.setReportboard_comment(rs.getString("reportboard_comment"));
				dto.setReportboard_date(rs.getString("reportboard_date"));
				dto.setReportboard_del(rs.getInt("reportboard_del"));
				dto.setReportboard_count(rs.getInt("reportboard_count"));
				dto.setUser_no(rs.getLong("user_no"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setReportboard_commentcount(rs.getInt("reportboard_commentcount"));
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

	public List<ReportboardcommentDTO> reportcommentList(long reportboard_no) {
		List<ReportboardcommentDTO> list = new ArrayList<ReportboardcommentDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reportboardcommentview WHERE reportboard_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reportboard_no);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				ReportboardcommentDTO dto = new ReportboardcommentDTO();
				dto.setReportboardcomment_no(rs.getLong("reportboardcomment_no"));
				dto.setReportboard_no(rs.getInt("reportboard_no"));
				dto.setReportboardcomment_comment(rs.getString("reportboardcomment_comment"));
				dto.setUser_no(rs.getLong("user_no"));
				dto.setReportboardcomment_date(rs.getString("reportboardcomment_date"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_name(rs.getString("user_name"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public void Reportupdate(ReportboardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboard SET reportboard_title=?, reportboard_comment=? " + "WHERE reportboard_no=? AND user_no=("
				+ "SELECT user_no FROM user WHERE user_id=?)";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getReportboard_title());
			pstmt.setString(2, dto.getReportboard_comment());
			pstmt.setLong(3, dto.getReportboard_no());
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

	public ReportboardcommentDTO ReportcommentDetail(ReportboardcommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reportboardcommentview WHERE reportboardcomment_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getReportboardcomment_no());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setReportboardcomment_comment(rs.getString("reportboardcomment_comment"));
				dto.setReportboardcomment_date(rs.getString("reportboardcomment_date"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_name(rs.getString("user_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public void ReportcommentUpdate(ReportboardcommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboardcomment SET reportboardcomment_comment=? WHERE reportboardcomment_no=? "
				+ "AND user_no=(SELECT user_no FROM user WHERE user_id=?)";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getReportboardcomment_comment());
			pstmt.setLong(2, dto.getReportboardcomment_no());
			pstmt.setString(3, dto.getUser_id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void Reportcdel(ReportboardcommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboardcomment SET reportboardcomment_del=1 "
				+ "WHERE reportboardcomment_no=? AND user_no="
				+ "(SELECT user_no FROM user WHERE user_id=?)";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getReportboardcomment_no());//물음표에  
			pstmt.setString(2, dto.getUser_id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void ReportpostDel(ReportboardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reportboard SET reportboard_del=1 " + "WHERE reportboard_no=? AND user_no="
				+ "(SELECT user_no FROM user WHERE user_id=?)";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getReportboard_no());
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

	public void ReportcommentWrite(ReportboardcommentDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO reportboardcomment (reportboard_no, reportboardcomment_comment, user_no) "
				+ "VALUES (?, ?, (SELECT user_no FROM user WHERE user_id=?))";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getReportboard_no());
			pstmt.setString(2, dto.getReportboardcomment_comment());
			pstmt.setString(3, dto.getUser_id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}



	
}