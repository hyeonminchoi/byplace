package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.byplace.dto.CategoryDTO;

import dbConnection.DB;

public class CategoryDAO {
	public static List<CategoryDTO> findAll() throws Exception{
		String sql = "SELECT * FROM category";
		List<CategoryDTO> list = new ArrayList<>();
		try(Connection connection = DB.getConnection("project");
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setCategory_no(rs.getLong("category_no"));
				categoryDTO.setCategory_category(rs.getString("category_category"));
				list.add(categoryDTO);
			}
			return list;
		}
	}
}
