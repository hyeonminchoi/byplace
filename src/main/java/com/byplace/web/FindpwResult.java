package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.UserDAO;


@WebServlet("/findpwResult")
public class FindpwResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FindpwResult() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		long user_no = Long.parseLong(request.getParameter("user_no"));
		String password = request.getParameter("password");
		
		UserDAO dao = new UserDAO();
		int result = dao.changePw(user_no, password);
		if(result>0) { // 변경 성공
			response.getWriter().println("<script>alert('비밀번호 변경에 성공했습니다.'); location.href='./login.jsp';</script>");
		} else { // 변경 실패
			response.getWriter().println("<script>alert('비밀번호 변경에 실패했습니다.'); location.href='./findpw.jsp';</script>");			
		}
		
	}

}
