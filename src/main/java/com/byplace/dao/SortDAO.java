package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.byplace.db.DBConnection;

public class SortDAO {
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
	
	public String findCategoryList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT sort_sort FROM sort WHERE sort_table = 'categoryList'";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getString("sort_sort");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs,pstmt);
		}
		return null;
	}

	public void editSort(String table, String sort) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT sort_sort FROM sort WHERE sort_table=?";
		
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, table);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //값 존재 -> 업데이트
				sql = "UPDATE sort SET sort_sort = ? WHERE sort_table = ?";
				pstmt.setString(1, sort);
				pstmt.setString(2, table);
				pstmt.execute();
			} else { // 값 X -> 추가
				sql = "INSERT INTO sort(sort_table, sort_sort) VALUES(?,?)";
				pstmt.setString(1, table);
				pstmt.setString(2, sort);
				pstmt.execute();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs,pstmt);
		}
	}

}
