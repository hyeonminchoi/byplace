package com.byplace.admin.web.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.admin.dao.AdminUserDAO;
import com.byplace.admin.util.LoginManager;
import com.byplace.dto.UserDTO;

@WebServlet("/adminUserLogout")
public class AdminUserLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminUserLogout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String user_id = request.getParameter("user_id");
			LoginManager loginManager = LoginManager.getInstance();
			loginManager.removeSession(user_id);
			writer.println("<script>window.location.href = document.referrer;</script>");
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
