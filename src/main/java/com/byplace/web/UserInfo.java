package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.UserDAO;


@WebServlet("/userInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("USER")!=null) {
			if(request.getParameter("user_no")!=null && request.getParameter("password")!=null) {
				long user_no = Long.parseLong(request.getParameter("user_no"));
				String password = request.getParameter("password");
				UserDAO dao = new UserDAO();
				int result = dao.passwordCheck(user_no, password);
				if(result>0) { //비밀번호가 맞는 경우
					response.sendRedirect("./userInfo.jsp");
				} else { //비밀번호 틀린 경우
					response.sendRedirect("./index.jsp");
				}
			}
		}
		
	}

}
