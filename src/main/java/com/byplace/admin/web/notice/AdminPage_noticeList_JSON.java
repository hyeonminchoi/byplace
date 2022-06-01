package com.byplace.admin.web.notice;

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

import com.byplace.admin.dao.AdminNoticeDAO;
import com.byplace.dto.NoticeDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_noticeList_JSON")
public class AdminPage_noticeList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_noticeList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			String searchColumn = "notice_title";
			if(request.getParameter("searchColumn")!=null && !request.getParameter("searchColumn").equals(""))
				searchColumn = request.getParameter("searchColumn");
			String searchValue = request.getParameter("searchValue");
			
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			if(sort.equals("notice_no asc"))
				cmd = "notice_no asc";
			else if(sort.equals("notice_no desc"))
				cmd = "notice_no desc";
			else if(sort.equals("notice_title asc"))
				cmd = "notice_title asc";
			else if(sort.equals("notice_title desc"))
				cmd = "notice_title desc";
			else if(sort.equals("notice_date asc"))
				cmd = "notice_date asc";
			else if(sort.equals("notice_date desc"))
				cmd = "notice_date desc";
			else if(sort.equals("notice_del asc"))
				cmd = "notice_del asc";
			else if(sort.equals("notice_del desc"))
				cmd = "notice_del desc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("noticeListColumn", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("noticeListColumn_sort", st.nextToken());
				response.addCookie(cookie);
			}
			
			AdminNoticeDAO noticeDAO = new AdminNoticeDAO();
			List<NoticeDTO> noticeList = noticeDAO.findAll(cmd, searchColumn, searchValue, currentPage, pageSize);
			String json = new Gson().toJson(noticeList);
			
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
