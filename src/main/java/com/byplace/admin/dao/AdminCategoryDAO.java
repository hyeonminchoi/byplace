package com.byplace.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.byplace.admin.util.categorySearchColumn;
import com.byplace.db.DBConnection;
import com.byplace.dto.CategoryDTO;

public class AdminCategoryDAO {
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

	//카테고리 리스트 읽어오기(페이징)
	public List<CategoryDTO> findAll(String sort, String searchColumn, String searchValue, int currentPage, int pageSize) {
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(categorySearchColumn.class, searchColumn))
				sql = "SELECT * FROM category WHERE " + searchColumn + " LIKE ? ORDER BY " + sort + " LIMIT ?, ?";
		List<CategoryDTO> list = new ArrayList<>();
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
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setCategory_no(rs.getLong("category_no"));
				categoryDTO.setCategory_category(rs.getString("category_category"));
				list.add(categoryDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}
	
	//카테고리 리스트 읽어오기
		public List<CategoryDTO> findAll() {
			String sql = "SELECT * FROM category";
			List<CategoryDTO> list = new ArrayList<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBConnection.dbConn();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					CategoryDTO categoryDTO = new CategoryDTO();
					categoryDTO.setCategory_no(rs.getLong("category_no"));
					categoryDTO.setCategory_category(rs.getString("category_category"));
					list.add(categoryDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(rs, pstmt);
			}
			return list;
		}

	//카테고리 테이블 갯수 구하기
	public int count(String searchColumn, String searchValue) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(EnumUtils.isValidEnumIgnoreCase(categorySearchColumn.class, searchColumn))
			sql = "SELECT COUNT(*) FROM category WHERE " + searchColumn + " LIKE ?";
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

	//카테고리 추가
	public int insert(String category_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO category(category_category) VALUES(?)";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category_name);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	//카테고리 삭제
	public int delete(int category_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM category WHERE category_no=?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, category_no);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

	//카테고리 수정
	public int edit(int category_no, String category_category) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE category SET category_category = ? WHERE category_no = ?";
		int result = 0;
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category_category);
			pstmt.setInt(2, category_no);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
		} finally {
			close(null, pstmt);
		}
		return result;
	}

}
