package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.UserDAO;
import com.byplace.dto.UserDTO;


@WebServlet("/findpw")
public class Findpw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Findpw() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDTO dto = new UserDTO();
		dto.setUser_name(request.getParameter("name"));
		dto.setUser_id(request.getParameter("id"));
		dto.setUser_phone(request.getParameter("phone"));
		
		UserDAO dao = new UserDAO();
		long result = dao.findpw(dto);
		if(result>0) { //개인정보 맞은 경우
			request.setAttribute("user_no", result);
			request.getRequestDispatcher("./findpwResult.jsp").forward(request, response);

		} else { //개인정보 틀린 경우
			request.setAttribute("error", "개인정보가 일치하지 않습니다.");
			request.getRequestDispatcher("./findpw.jsp").forward(request, response);
		}
	}

}
