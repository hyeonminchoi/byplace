package com.byplace.web.admin.restaurant;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.admin.AdminRestaurantDAO;
import com.byplace.dto.UserDTO;

@WebServlet("/adminRestaurantRefuse")
public class AdminRestaurantRefuse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminRestaurantRefuse() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			long restaurant_no = Long.parseLong(request.getParameter("restaurant_no"));
			AdminRestaurantDAO adminRestaurantDAO = new AdminRestaurantDAO();
			
			int result = adminRestaurantDAO.refuse(restaurant_no);
			if(result==1) { //음식점 거부
				writer.println("<script>alert('" + "음식점 거부에 성공했습니다" + "'); location.href='./adminPage_restaurantApprovalList';</script>");
			} else { //실패
				writer.println("<script>alert('" + "음식점 거부에 실패했습니다" + "'); location.href='./adminPage_restaurantApprovalList';</script>");
			}
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
