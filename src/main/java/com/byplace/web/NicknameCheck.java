package com.byplace.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.UserDAO;


@WebServlet("/nicknameCheck")
public class NicknameCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NicknameCheck() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("nickname")!= null) {
			String nickname = request.getParameter("nickname");
			UserDAO dao = new UserDAO();
			int result = 1;
			result = dao.nicknameCheck(nickname);
			PrintWriter pw = response.getWriter();
			pw.print(result);
		}
	}

}
