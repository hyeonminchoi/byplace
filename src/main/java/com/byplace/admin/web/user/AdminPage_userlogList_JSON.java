package com.byplace.admin.web.user;

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

import com.byplace.admin.dao.AdminUserDAO;
import com.byplace.dto.UserDTO;
import com.byplace.dto.UserlogDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_userlogList_JSON")
public class AdminPage_userlogList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_userlogList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			String searchColumn = "user_id";
			if(request.getParameter("searchColumn")!=null && !request.getParameter("searchColumn").equals(""))
				searchColumn = request.getParameter("searchColumn");
			String searchValue = request.getParameter("searchValue");
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			if(sort.equals("userlog_no desc"))
				cmd = "userlog_no desc";
			else if(sort.equals("userlog_no asc"))
				cmd = "userlog_no asc";
			else if(sort.equals("user_id desc"))
				cmd = "user_id desc";
			else if(sort.equals("user_id asc"))
				cmd = "user_id asc";
			else if(sort.equals("userlog_status desc"))
				cmd = "userlog_status desc";
			else if(sort.equals("userlog_status asc"))
				cmd = "userlog_status asc";
			else if(sort.equals("userlog_date desc"))
				cmd = "userlog_date desc";
			else if(sort.equals("userlog_date asc"))
				cmd = "userlog_date asc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("userlogListColumn", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("userlogListColumn_sort", st.nextToken());
				response.addCookie(cookie);
			}
			
			AdminUserDAO userDAO = new AdminUserDAO();
			List<UserlogDTO> userList = userDAO.findLogAll(cmd, searchColumn, searchValue, currentPage, pageSize);
			String json = new Gson().toJson(userList);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} else {
			response.sendRedirect("./index.jsp");
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./adminPage.jsp");
	}

}
