package com.byplace.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.RestaurantDAO;
import com.byplace.dto.CategoryDTO;
import com.byplace.dto.RestaurantDTO;
import com.byplace.dto.UserDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/resup")
public class Resup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Resup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RestaurantDAO dao = new RestaurantDAO();
		List<CategoryDTO> categorylist = dao.categorylist();
		RequestDispatcher rd = request.getRequestDispatcher("./restaurantupdate.jsp");
		request.setAttribute("restaurant_no", request.getParameter("restaurant_no"));
		request.setAttribute("catelist",categorylist);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null) {
			String url = session.getServletContext().getRealPath("/restaurantImage");
			MultipartRequest mr = new MultipartRequest(
					request, url, 10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			long restaurant_no = Long.parseLong(mr.getParameter("restaurant_no"));
			String restaurant_image = mr.getFilesystemName("restaurant_image");
			String restaurant_name = mr.getParameter("restaurant_name");
			String restaurant_description = mr.getParameter("restaurant_description");
			String restaurant_postcode = mr.getParameter("restaurant_postcode");
			String restaurant_roadAddress = mr.getParameter("restaurant_roadAddress");
			String restaurant_detailAddress = mr.getParameter("restaurant_detailAddress");
			String restaurant_extraAddress = mr.getParameter("restaurant_extraAddress");
			String category_category = mr.getParameter("category_category");
			
			
			RestaurantDTO dto = new RestaurantDTO();
			dto.setRestaurant_name(restaurant_name);
			dto.setRestaurant_description(restaurant_description);
			dto.setRestaurant_postcode(restaurant_postcode);
			dto.setRestaurant_roadAddress(restaurant_roadAddress);
			dto.setRestaurant_detailAddress(restaurant_detailAddress);
			dto.setRestaurant_extraAddress(restaurant_extraAddress);
			dto.setCategory_category(category_category);
			dto.setRestaurant_image(restaurant_image);
			dto.setRestaurant_no(restaurant_no);
			
			RestaurantDAO dao = new RestaurantDAO();
			dao.resup(dto);
			response.sendRedirect("./restaurantdetail?restaurant_no=" + restaurant_no);
		} else {
			response.sendRedirect("./login.jsp");
		}
		
	}

}
