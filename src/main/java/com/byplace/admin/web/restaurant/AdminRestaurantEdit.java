package com.byplace.admin.web.restaurant;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.admin.dao.AdminRestaurantDAO;
import com.byplace.dto.RestaurantDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/adminRestaurantEdit")
public class AdminRestaurantEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminRestaurantEdit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		String url = session.getServletContext().getRealPath("/restaurantImage");
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			long restaurant_no = Long.parseLong(request.getParameter("restaurant_no"));
			String restaurant_name = request.getParameter("restaurant_name");
			String restaurant_description = request.getParameter("restaurant_description");
			String restaurant_postcode = request.getParameter("restaurant_postcode");
			String restaurant_roadAddress = request.getParameter("restaurant_roadAddress");
			String restaurant_detailAddress = request.getParameter("restaurant_detailAddress");
			String restaurant_extraAddress = request.getParameter("restaurant_extraAddress");
			String category_category = request.getParameter("category_category");
			
			RestaurantDTO dto = new RestaurantDTO();
			dto.setRestaurant_no(restaurant_no);
			dto.setRestaurant_name(restaurant_name);
			dto.setRestaurant_description(restaurant_description);
			dto.setRestaurant_postcode(restaurant_postcode);
			dto.setRestaurant_roadAddress(restaurant_roadAddress);
			dto.setRestaurant_detailAddress(restaurant_detailAddress);
			dto.setRestaurant_extraAddress(restaurant_extraAddress);
			dto.setCategory_category(category_category);
			
			AdminRestaurantDAO adminRestaurantDAO = new AdminRestaurantDAO();
			int result = adminRestaurantDAO.edit(dto);
			if(result==1) { //수정 성공
				writer.println("<script>alert('" + "수정에 성공했습니다" + "'); window.location.href = document.referrer;</script>");
			} else { //수정 실패
				writer.println("<script>alert('" + "수정에 실패했습니다" + "'); window.location.href = document.referrer;</script>");
			}
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

}
