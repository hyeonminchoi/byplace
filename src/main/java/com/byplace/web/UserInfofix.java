package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.UserDAO;
import com.byplace.dto.UserDTO;


@WebServlet("/userInfofix")
public class UserInfofix extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
  
    public UserInfofix() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//여기서 dao 호출해서 update로 수정
		request.setCharacterEncoding("UTF-8");
		UserDAO dao = new UserDAO();
		
		long no = Long.parseLong(request.getParameter("user_no"));
		String name = request.getParameter("user_name");
		String nickname = request.getParameter("user_nickname");
		String phone = request.getParameter("user_phone");
		String email = request.getParameter("user_email");
		String postcode = request.getParameter("user_postcode");
		String roadAddress = request.getParameter("user_roadAddress");
		String detailAddress = request.getParameter("user_detailAddress");
		String extraAddress = request.getParameter("user_extraAddress");
		
		UserDTO dto = new UserDTO();
		
		dto.setUser_no(no);
		dto.setUser_name(name);
		dto.setUser_nickname(nickname);
		dto.setUser_phone(phone);
		dto.setUser_email(email);
		dto.setUser_postcode(postcode);
		dto.setUser_roadAddress(roadAddress);
		dto.setUser_detailAddress(detailAddress);
		dto.setUser_extraAddress(extraAddress);
		
		
		int result = dao.userfix(dto);
		if(result>0) {
			response.sendRedirect("./userInfo.jsp");
		} else {
			response.sendRedirect("./userInfo.jsp");
		}
	}

}
