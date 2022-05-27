package com.byplace.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.RestaurantDAO;
import com.byplace.dto.CategoryDTO;

@WebServlet("/category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Category() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		RestaurantDAO dao = new RestaurantDAO();
		List<CategoryDTO> categorylist = dao.categorylist();
		RequestDispatcher rd = request.getRequestDispatcher("./restaurantadd.jsp");
		request.setAttribute("catelist",categorylist);
		rd.forward(request, response);
	}

}
