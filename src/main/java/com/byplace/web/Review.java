package com.byplace.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.RestaurantDAO;
import com.byplace.dto.ReviewDTO;
import com.byplace.dto.UserDTO;

@WebServlet("/review")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Review() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null) {
			String review_comment = request.getParameter("review_comment");
			String review_date = request.getParameter("review_date");
			double review_rating = Double.parseDouble(request.getParameter("review_rating"));
			long restaurant_no = Long.parseLong(request.getParameter("restaurant_no"));
			long user_no = Long.parseLong(request.getParameter("user_no"));
			System.out.println(review_date);
			System.out.println(restaurant_no);
			ReviewDTO dto = new ReviewDTO();
			dto.setRestaurant_no(restaurant_no);
			dto.setUser_no(user_no);
			dto.setReview_comment(review_comment);
			dto.setReview_date(review_date);
			dto.setReview_rating(review_rating);
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			RestaurantDAO dao = new RestaurantDAO();
			dao.review(dto);
			
			response.sendRedirect("./restaurantdetail?restaurant_no=" + restaurant_no);
		} else {
			response.sendRedirect("./login.jsp");
		}
	}

}
