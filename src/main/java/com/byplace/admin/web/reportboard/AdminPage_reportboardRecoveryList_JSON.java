package com.byplace.admin.web.reportboard;

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

import com.byplace.admin.dao.AdminReportboardDAO;
import com.byplace.dto.ReportboardDTO;
import com.byplace.dto.UserDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_reportboardRecoveryList_JSON")
public class AdminPage_reportboardRecoveryList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_reportboardRecoveryList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			String searchColumn = "reportboard_title";
			if(request.getParameter("searchColumn")!=null && !request.getParameter("searchColumn").equals(""))
				searchColumn = request.getParameter("searchColumn");
			String searchValue = request.getParameter("searchValue");
			
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			if(sort.equals("reportboard_no asc"))
				cmd = "reportboard_no asc";
			else if(sort.equals("reportboard_no desc"))
				cmd = "reportboard_no desc";
			else if(sort.equals("reportboard_title asc"))
				cmd = "reportboard_title asc";
			else if(sort.equals("reportboard_title desc"))
				cmd = "reportboard_title desc";
			else if(sort.equals("reportboard_date asc"))
				cmd = "reportboard_date asc";
			else if(sort.equals("reportboard_date desc"))
				cmd = "reportboard_date desc";
			else if(sort.equals("reportboard_comment desc"))
				cmd = "reportboard_comment desc";
			else if(sort.equals("reportboard_comment asc"))
				cmd = "reportboard_comment asc";
			else if(sort.equals("user_id desc"))
				cmd = "user_id desc";
			else if(sort.equals("user_id asc"))
				cmd = "user_id asc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("reportboardRecoveryListColumn", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("reportboardRecoveryListColumn_sort", st.nextToken());
				response.addCookie(cookie);
			}
			
			AdminReportboardDAO reportboardDAO = new AdminReportboardDAO();
			List<ReportboardDTO> reportboardList = reportboardDAO.findRecovery(cmd, searchColumn, searchValue, currentPage, pageSize);
			String json = new Gson().toJson(reportboardList);
			
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
