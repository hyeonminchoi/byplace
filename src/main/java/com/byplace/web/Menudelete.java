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
import com.byplace.dto.UserDTO;

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
			FoodDTO dto = new FoodDTO();
			
			dto.setFood_no(Long.parseLong(request.getParameter("food_no")));
			
			RestaurantDAO dao = new RestaurantDAO();
			dao.menudelete(dto);
			
		}else {
			response.sendRedirect("./login.jsp");
		}
		response.sendRedirect("./restaurantdetail?restaurant_no=" + request.getParameter("restaurant_no"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
