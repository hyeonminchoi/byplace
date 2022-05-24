package com.byplace.web.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.CategoryDAO;

@WebServlet("/adminPage_categoryList")
public class AdminPage_categoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_categoryList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER")!=null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			CategoryDAO dao = new CategoryDAO();
			request.setAttribute("recordCount", dao.count());
			request.setAttribute("pageSize", 2);
			if(request.getParameter("pg")==null)
				request.setAttribute("pg", "1");
			else
				request.setAttribute("pg", request.getParameter("pg"));
			request.getRequestDispatcher("./adminPage_categoryList.jsp").forward(request, response);
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
