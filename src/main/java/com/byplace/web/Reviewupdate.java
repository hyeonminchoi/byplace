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

@WebServlet("/reviewupdate")
public class Reviewupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Reviewupdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long restaurant_no = Long.parseLong(request.getParameter("restaurant_no"));
		long review_no = Long.parseLong(request.getParameter("review_no"));
		request.setAttribute("review_no", review_no);
		request.setAttribute("restaurant_no", restaurant_no);
		request.getRequestDispatcher("./reviewupdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
				request.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession();
				if(session.getAttribute("USER") != null) {
					String review_comment = request.getParameter("review_comment");
					double review_rating = Double.parseDouble(request.getParameter("starRev"));
					long review_no = Long.parseLong(request.getParameter("review_no"));
					long restaurant_no = Long.parseLong(request.getParameter("restaurant_no"));
					ReviewDTO dto = new ReviewDTO();
					dto.setReview_comment(review_comment);
					dto.setReview_rating(review_rating);
					dto.setReview_no(review_no);
					RestaurantDAO dao = new RestaurantDAO();
					dao.reviewupdate(dto);
					
					response.sendRedirect("./restaurantdetail?restaurant_no=" + restaurant_no);
				} else {
					response.sendRedirect("./login.jsp");
				}
	}

}
