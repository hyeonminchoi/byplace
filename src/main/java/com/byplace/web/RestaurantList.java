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
import com.byplace.dto.RestaurantDTO;


@WebServlet("/restaurantList")
public class RestaurantList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RestaurantList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		RestaurantDAO dao = new RestaurantDAO();
		List<RestaurantDTO> rList = dao.rList();
		RequestDispatcher rd = request.getRequestDispatcher("./restaurantList.jsp");
		request.setAttribute("reslist", rList);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
