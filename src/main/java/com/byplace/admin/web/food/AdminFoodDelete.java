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

@WebServlet("/adminFoodDelete")
public class AdminFoodDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminFoodDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			long food_no = Long.parseLong(request.getParameter("food_no"));
			AdminFoodDAO adminFoodDAO = new AdminFoodDAO();
			int result = adminFoodDAO.delete(food_no);
			if(result==1) { //삭제 성공
				writer.println("<script>alert('" + "삭제에 성공했습니다" + "'); window.location.href = document.referrer;</script>");
			} else { //삭제 실패
				writer.println("<script>alert('" + "삭제에 실패했습니다" + "'); window.location.href = document.referrer;</script>");
			}
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
