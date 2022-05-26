package com.byplace.web.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.admin.AdminUserDAO;
import com.byplace.dto.UserDTO;

@WebServlet("/adminUserRelease")
public class AdminUserRelease extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminUserRelease() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			int user_no = Integer.parseInt(request.getParameter("user_no"));
			AdminUserDAO adminUserDAO = new AdminUserDAO();
			
			int result = adminUserDAO.accept(user_no);
			if(result==1) { //추가 성공
				writer.println("<script>alert('" + "블랙리스트 해제에 성공했습니다" + "'); location.href='./adminPage_userBlackList';</script>");
			} else { //추가 실패
				writer.println("<script>alert('" + "블랙리스트 해제에 실패했습니다" + "'); location.href='./adminPage_userBlackList';</script>");
			}
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
