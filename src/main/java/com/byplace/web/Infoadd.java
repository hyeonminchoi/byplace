package com.byplace.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.RestaurantDAO;
import com.byplace.dto.RestaurantinfoDTO;

@WebServlet("/infoadd")
public class Infoadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Infoadd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null) {
			long restaurant_no = Long.parseLong(request.getParameter("restaurant_no"));
			long restaurantinfo_no = Long.parseLong(request.getParameter("restaurantinfo_no"));
			RestaurantinfoDTO dto = new RestaurantinfoDTO();
			dto.setRestaurantinfo_no(restaurantinfo_no);
			dto.setRestaurantinfo_businessnumber(request.getParameter("restaurantinfo_businessnumber"));
			dto.setRestaurantinfo_openinghours(request.getParameter("restaurantinfo_openinghours"));
			dto.setRestaurantinfo_description(request.getParameter("restaurantinfo_description"));
			dto.setRestaurant_no(restaurant_no);
			RestaurantDAO dao = new RestaurantDAO();
			dao.infoadd(dto);
			
			response.sendRedirect("./restaurantdetail?restaurant_no=" + restaurant_no);
		} else {
			response.sendRedirect("./login.jsp");
		}
	}

}
