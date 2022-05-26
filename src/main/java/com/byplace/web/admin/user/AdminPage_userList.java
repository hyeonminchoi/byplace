package com.byplace.web.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.admin.AdminUserDAO;

@WebServlet("/adminPage_userList")
public class AdminPage_userList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_userList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER")!=null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			AdminUserDAO dao = new AdminUserDAO();
			request.setAttribute("recordCount", dao.count());
			request.setAttribute("pageSize", 20);
			
			Cookie cookie[] = request.getCookies();
			String sort = "user_no asc";
			String column="", column_sort=""; 
			if(cookie != null) {
				for(int i=0;i<cookie.length;i++) {
					if(cookie[i].getName().equals("column")) {
						column = cookie[i].getValue(); 
					}
					if(cookie[i].getName().equals("column_sort")) {
						column_sort = cookie[i].getValue();
					}
				}
				if(!column.equals("") && !column_sort.equals("")) {
					sort = column + " " + column_sort;
				}
			}
			
			if(request.getParameter("pg")==null)
				request.setAttribute("pg", "1");
			else
				request.setAttribute("pg", request.getParameter("pg"));
			request.setAttribute("sort", sort);
			request.getRequestDispatcher("./adminPage_userList.jsp").forward(request, response);
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
