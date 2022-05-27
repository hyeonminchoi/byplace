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
import com.byplace.util.Util;

@WebServlet("/menuadd")
public class Menuadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Menuadd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id") != null
				&& request.getParameter("restaurant_no") != null
				&& Util.str2Int(request.getParameter("restaurant_no"))) {
			
			FoodDTO dto = new FoodDTO();
			dto.setFood_name(request.getParameter("food_name"));
			dto.setFood_description(request.getParameter("food_description"));
			dto.setFood_price(Integer.parseInt(request.getParameter("food_price")));
			dto.setFood_image(request.getParameter("food_image"));
			
		} else {
			
		}
	}

}
