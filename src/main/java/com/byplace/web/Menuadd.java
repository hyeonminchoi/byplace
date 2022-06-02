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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/menuadd")
public class Menuadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Menuadd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null) {
			String url = session.getServletContext().getRealPath("/menuImage");
			MultipartRequest mr = new MultipartRequest(
					request, url, 10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			String food_name = mr.getParameter("food_name");
			String food_description = mr.getParameter("food_description");
			String food_image = mr.getFilesystemName("food_image");
			Long restaurant_no = Long.parseLong(mr.getParameter("restaurant_no"));
			FoodDTO dto = new FoodDTO();
			dto.setRestaurant_no(restaurant_no);
			dto.setFood_name(food_name);
			dto.setFood_description(food_description);
			dto.setFood_price(Integer.parseInt(mr.getParameter("food_price")));
			dto.setFood_image(food_image);
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			RestaurantDAO dao = new RestaurantDAO();
			dao.menuadd(dto);
			
			response.sendRedirect("./restaurantdetail?restaurant_no=" + restaurant_no);
		} else {
			response.sendRedirect("./login.jsp");
		}
	}

}
