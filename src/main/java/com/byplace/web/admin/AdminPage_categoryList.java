package com.byplace.web.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dto.CategoryDTO;
import com.byplace.dto.UserDTO;

@WebServlet("/adminPage_categoryList")
public class AdminPage_categoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_categoryList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			CategoryDAO categoryDAO = new CategoryDAO();
			CategoryDTO categoryList = categoryDAO.findAll();
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("./adminPage_categoryList.jsp").forward(request, response);
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./adminPage.jsp");
	}

}
