package com.byplace.admin.web.food;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.admin.dao.AdminFoodDAO;
import com.byplace.dto.FoodDTO;
import com.byplace.dto.UserDTO;

@WebServlet("/adminFoodEdit")
public class AdminFoodEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminFoodEdit() {
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
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			long restaurant_no = Long.parseLong(request.getParameter("restaurant_no"));
			long food_no = Long.parseLong(request.getParameter("food_no"));
			String food_name = request.getParameter("food_name");
			String food_description = request.getParameter("food_description");
			int food_price = Integer.parseInt(request.getParameter("food_price"));
			
			FoodDTO dto = new FoodDTO();
			dto.setRestaurant_no(restaurant_no);
			dto.setFood_no(food_no);
			dto.setFood_price(food_price);
			dto.setFood_description(food_description);
			dto.setFood_name(food_name);
			
			AdminFoodDAO adminFoodDAO = new AdminFoodDAO();
			int result = adminFoodDAO.edit(dto);
			if(result==1) { //수정 성공
				writer.println("<script>alert('" + "수정에 성공했습니다" + "'); window.location.href = document.referrer;</script>");
			} else { //수정 실패
				writer.println("<script>alert('" + "수정에 실패했습니다" + "'); window.location.href = document.referrer;</script>");
			}
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

}
