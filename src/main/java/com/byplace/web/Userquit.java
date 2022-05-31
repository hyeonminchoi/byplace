package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.UserDAO;


@WebServlet("/userquit")
public class Userquit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Userquit() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Long user_no = Long.parseLong(request.getParameter("user_no"));
		UserDAO dao = new UserDAO();
		dao.quit(user_no);
		response.sendRedirect("./logout");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
