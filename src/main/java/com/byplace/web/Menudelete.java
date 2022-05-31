package com.byplace.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.RestaurantDAO;
import com.byplace.dto.FoodDTO;

@WebServlet("/menudelete")
public class Menudelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Menudelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null) {
			long restaurant_no = Long.parseLong(request.getParameter("restaurant_no"));
			FoodDTO dto = new FoodDTO();
			dto.setFood_del(Integer.parseInt(request.getParameter("food_del")));
			dto.setRestaurant_no(restaurant_no);
			RestaurantDAO dao = new RestaurantDAO();
			dao.menudelete(dto);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
