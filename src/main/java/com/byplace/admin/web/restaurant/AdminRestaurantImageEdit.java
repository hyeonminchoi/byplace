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
import com.byplace.dto.UserDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/adminRestaurantImageEdit")
public class AdminRestaurantImageEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminRestaurantImageEdit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		String url = session.getServletContext().getRealPath("/restaurantImage");
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			MultipartRequest multipartRequest = new MultipartRequest(
					request, url, 10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			long restaurant_no = Long.parseLong(multipartRequest.getParameter("restaurant_no"));
			String restaurant_image = multipartRequest.getFilesystemName("restaurant_image");
			
			RestaurantDTO dto = new RestaurantDTO();
			dto.setRestaurant_no(restaurant_no);
			dto.setRestaurant_image(restaurant_image);
			
			AdminRestaurantDAO adminRestaurantDAO = new AdminRestaurantDAO();
			int result = adminRestaurantDAO.imageEdit(dto);
			if(result==1) { //추가 성공
				writer.println("<script>alert('" + "수정에 성공했습니다" + "'); window.location.href = document.referrer;</script>");
			} else { //추가 실패
				writer.println("<script>alert('" + "수정에 실패했습니다" + "'); window.location.href = document.referrer;</script>");
			}
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

}
