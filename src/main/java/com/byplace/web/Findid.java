package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.UserDAO;
import com.byplace.dto.UserDTO;


@WebServlet("/findid")
public class Findid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Findid() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 try {
	         //입력 : member_nick , member_phone , member_birth  -----> UserDto
	         request.setCharacterEncoding("UTF-8");
	         UserDTO dto = new UserDTO();
	         dto.setUser_name(request.getParameter("user_name"));
	         dto.setUser_phone(request.getParameter("user_phone"));
	         
	         //처리
	         UserDAO dao = new UserDAO();
	         String user_id = dao.findId(dto);
	         
	         //출력
	         if(user_id != null) {//결과가 있으면(정보가 맞다면)
	            response.sendRedirect("findidResult.jsp?user_id="+user_id);
	            
	            request.getSession().setAttribute("user_id", user_id);
	            response.sendRedirect("findidResult.jsp");
	         }
	         else {//결과가 없으면(정보가 맞지 않으면)
	            response.sendRedirect("find_id.jsp?error");
	         }
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         response.sendError(500);
	      }
	   }
	}
