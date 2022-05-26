package com.byplace.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.RestaurantDAO;
import com.byplace.dto.RestaurantDTO;
import com.byplace.util.Util;



@WebServlet("/restaurantdetail")
public class Restaurantdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Restaurantdetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("restaurant_no") != null
				&& Util.str2Int(request.getParameter("restaurant_no"))) {
			int restaurant_no = Integer.parseInt(request.getParameter("restaurant_no"));
			RestaurantDAO dao = new RestaurantDAO();
			RestaurantDTO resdetail = dao.resdetail(restaurant_no);
			
			RequestDispatcher rd = request.getRequestDispatcher("/restaurantdetail.jsp");
			request.setAttribute("resdetail", resdetail);
			rd.forward(request, response);
			
		}else {
			response.sendRedirect("./restaurantlist");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
