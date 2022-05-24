package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.LoginDAO;
import com.byplace.dto.UserDTO;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("id") != null && request.getParameter("pw") != null) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			UserDTO userDTO = new UserDTO();
			userDTO.setUser_id(id);
			userDTO.setUser_password(pw);

			LoginDAO dao = new LoginDAO();
			userDTO = dao.login(userDTO);
			
			

			if (userDTO != null) {
				request.getSession().setAttribute("USER", userDTO);
				response.sendRedirect("./index.jsp");
			} else {
				request.setAttribute("error", "로그인에 실패했습니다.");
				request.getRequestDispatcher("./login.jsp").forward(request, response);
			}
		}
	}

}
