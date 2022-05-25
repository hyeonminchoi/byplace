package com.byplace.web.admin;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.admin.AdminCategoryDAO;
import com.byplace.dto.CategoryDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_categoryList_JSON")
public class AdminPage_categoryList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_categoryList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			if(sort.equals("category_no desc"))
				cmd = "category_no desc";
			else
				cmd = "category_no asc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("column", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("column_sort", st.nextToken());
				response.addCookie(cookie);
			}
			
			AdminCategoryDAO categoryDAO = new AdminCategoryDAO();
			List<CategoryDTO> categoryList = categoryDAO.findAll(cmd, currentPage, pageSize);
			
			String json = new Gson().toJson(categoryList);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./adminPage.jsp");
	}

}
