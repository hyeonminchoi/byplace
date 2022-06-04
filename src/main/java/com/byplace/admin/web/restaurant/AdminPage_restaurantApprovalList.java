package com.byplace.admin.web.restaurant;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.admin.dao.AdminRestaurantDAO;
import com.byplace.admin.util.pageConfigure;
import com.byplace.dto.UserDTO;

@WebServlet("/adminPage_restaurantApprovalList")
public class AdminPage_restaurantApprovalList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_restaurantApprovalList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER")!=null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String searchColumn = "category_category";
			if(request.getParameter("searchColumn")!=null)
				searchColumn = request.getParameter("searchColumn");
			String searchValue = "";
			if(request.getParameter("searchValue")!=null)
				searchValue = request.getParameter("searchValue");
			AdminRestaurantDAO dao = new AdminRestaurantDAO();
			int recordCount = dao.countApproval(searchColumn, searchValue);
			request.setAttribute("recordCount", recordCount);
			request.setAttribute("pageSize", pageConfigure.pageSize);
			
			Cookie cookie[] = request.getCookies();
			String sort = "restaurant_no asc";
			String column="", column_sort=""; 
			if(cookie != null) {
				for(int i=0;i<cookie.length;i++) {
					if(cookie[i].getName().equals("restaurantApprovalListColumn")) {
						column = cookie[i].getValue(); 
					}
					if(cookie[i].getName().equals("restaurantApprovalListColumn_sort")) {
						column_sort = cookie[i].getValue();
					}
				}
				if(!column.equals("") && !column_sort.equals("")) {
					sort = column + " " + column_sort;
				}
			}
			
			String pg = request.getParameter("pg");
			if(pg==null)
				request.setAttribute("pg", "1");
			else if(Integer.parseInt(pg)>1 && (Integer.parseInt(pg)-1)*pageConfigure.pageSize>=recordCount) {
				request.setAttribute("pg", String.valueOf(Integer.parseInt(pg)-1));
			} else
				request.setAttribute("pg", pg);
			request.setAttribute("sort", sort);
			request.getRequestDispatcher("./adminPage_restaurantApprovalList.jsp").forward(request, response);
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
