package com.byplace.web.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.admin.AdminCategoryDAO;

@WebServlet("/adminCategoryEdit")
public class AdminCategoryEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminCategoryEdit() {
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
			int category_no = Integer.parseInt(request.getParameter("categoryNo"));
			String category_category = request.getParameter("categoryName");
			AdminCategoryDAO adminCategoryDAO = new AdminCategoryDAO();
			int result = adminCategoryDAO.edit(category_no, category_category);
			if(result==1) { //추가 성공
				writer.println("<script>alert('" + "수정에 성공했습니다" + "'); location.href='./adminPage_categoryList';</script>");
			} else { //추가 실패
				writer.println("<script>alert('" + "수정에 실패했습니다" + "'); location.href='./adminPage_categoryList';</script>");
			}
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

}
