package com.byplace.web.admin.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.admin.AdminUserDAO;
import com.byplace.dto.UserDTO;

@WebServlet("/adminUserEdit")
public class AdminUserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminUserEdit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			long user_no = Long.parseLong(request.getParameter("user_no"));
			AdminUserDAO adminUserDAO = new AdminUserDAO();
			UserDTO dto = new UserDTO();
			dto.setUser_no(user_no);
			dto.setUser_name(request.getParameter("user_name"));
			dto.setUser_nickname(request.getParameter("user_nickname"));
			dto.setUser_email(request.getParameter("user_email"));
			dto.setUser_postcode(request.getParameter("user_postcode"));
			dto.setUser_roadAddress(request.getParameter("user_roadAddress"));
			dto.setUser_detailAddress(request.getParameter("user_detailAddress"));
			dto.setUser_extraAddress(request.getParameter("user_extraAddress"));
			dto.setUser_birthday(request.getParameter("user_birthday"));
			dto.setUser_type(request.getParameter("user_type"));
			dto.setUser_phone(request.getParameter("user_phone"));
			int result = adminUserDAO.edit(dto);
			if(result==1) { //추가 성공
				writer.println("<script>alert('" + "수정에 성공했습니다" + "'); location.href='./adminPage_userList';</script>");
			} else { //추가 실패
				writer.println("<script>alert('" + "수정에 실패했습니다" + "'); location.href='./adminPage_userList';</script>");
			}
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

}
