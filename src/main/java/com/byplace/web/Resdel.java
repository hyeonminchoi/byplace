package com.byplace.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.RestaurantDAO;
import com.byplace.dto.RestaurantDTO;

@WebServlet("/resdel")
public class Resdel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Resdel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null) {
			RestaurantDTO dto = new RestaurantDTO();
			
			dto.setRestaurant_no(Long.parseLong(request.getParameter("restaurant_no")));
			
			RestaurantDAO dao = new RestaurantDAO();
			dao.resdel(dto);
			
		} else {
			response.sendRedirect("./login.jsp");
		}
		response.sendRedirect("./restaurantList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
