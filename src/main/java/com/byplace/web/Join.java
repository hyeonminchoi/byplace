package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.UserDAO;
import com.byplace.dto.UserDTO;



@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Join() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./join.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String postcode = request.getParameter("postcode");
		String roadaddress = request.getParameter("roadAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String birthday = request.getParameter("birthday");
		String type = request.getParameter("type");
		
		UserDTO dto = new UserDTO();
		
		dto.setUser_id(id);
		dto.setUser_password(password);
		dto.setUser_name(name);
		dto.setUser_nickname(nickname);
		dto.setUser_phone(phone);
		dto.setUser_email(email);
		dto.setUser_postcode(postcode);
		dto.setUser_roadAddress(roadaddress);
		dto.setUser_detailAddress(detailAddress);
		dto.setUser_extraAddress(extraAddress);
		dto.setUser_birthday(birthday);
		dto.setUser_type(type);
		
		//dao
		UserDAO dao = new UserDAO();
		int result = dao.join(dto);
		
		if(result>0) {
			response.sendRedirect("./login.jsp");
		} else {
			request.setAttribute("error", "회원가입에 실패했습니다.");
			request.getRequestDispatcher("./join.jsp").forward(request, response);
		}
		
	}

}