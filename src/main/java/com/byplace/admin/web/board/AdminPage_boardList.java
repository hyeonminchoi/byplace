package com.byplace.admin.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.admin.dao.AdminCategoryDAO;
import com.byplace.admin.dao.AdminBoardDAO;
import com.byplace.admin.util.pageConfigure;

@WebServlet("/adminPage_boardList")
public class AdminPage_boardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_boardList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER")!=null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String searchColumn = "board_title";
			if(request.getParameter("searchColumn")!=null)
				searchColumn = request.getParameter("searchColumn");
			String searchValue = "";
			if(request.getParameter("searchValue")!=null)
				searchValue = request.getParameter("searchValue");
			AdminBoardDAO dao = new AdminBoardDAO();
			int recordCount = dao.count(searchColumn, searchValue);
			request.setAttribute("recordCount", recordCount);
			request.setAttribute("pageSize", pageConfigure.pageSize);
			
			Cookie cookie[] = request.getCookies();
			String sort = "board_no asc";
			String column="", column_sort=""; 
			if(cookie != null) {
				for(int i=0;i<cookie.length;i++) {
					if(cookie[i].getName().equals("boardListColumn")) {
						column = cookie[i].getValue(); 
					}
					if(cookie[i].getName().equals("boardListColumn_sort")) {
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
			request.getRequestDispatcher("./adminPage_boardList.jsp").forward(request, response);
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
