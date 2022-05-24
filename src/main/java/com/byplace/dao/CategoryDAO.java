package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.byplace.db.DBConnection;
import com.byplace.dto.CategoryDTO;

public class CategoryDAO {
	public void close(ResultSet rs, PreparedStatement pstmt) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public List<CategoryDTO> findAll(String sort){
		String sql = "SELECT * FROM category ORDER BY " + sort;
		List<CategoryDTO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setCategory_no(rs.getLong("category_no"));
				categoryDTO.setCategory_category(rs.getString("category_category"));
				list.add(categoryDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return list;
	}
}
