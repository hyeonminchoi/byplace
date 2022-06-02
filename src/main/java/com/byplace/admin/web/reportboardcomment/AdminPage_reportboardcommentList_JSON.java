package com.byplace.admin.web.reportboardcomment;

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

import com.byplace.admin.dao.AdminReportboardcommentDAO;
import com.byplace.dto.ReportboardcommentDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_reportboardcommentList_JSON")
public class AdminPage_reportboardcommentList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_reportboardcommentList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			String searchColumn = "reportboardcomment_comment";
			if(request.getParameter("searchColumn")!=null && !request.getParameter("searchColumn").equals(""))
				searchColumn = request.getParameter("searchColumn");
			String searchValue = request.getParameter("searchValue");
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			if(sort.equals("reportboardcomment_no desc"))
				cmd = "reportboardcomment_no desc";
			else if(sort.equals("reportboardcomment_no asc"))
				cmd = "reportboardcomment_no asc";
			else if(sort.equals("user_id desc"))
				cmd = "user_id desc";
			else if(sort.equals("user_id asc"))
				cmd = "user_id asc";
			else if(sort.equals("reportboardcomment_date desc"))
				cmd = "reportboardcomment_date desc";
			else if(sort.equals("reportboardcomment_date asc"))
				cmd = "reportboardcomment_date asc";
			else if(sort.equals("reportboardcomment_del desc"))
				cmd = "reportboardcomment_del desc";
			else
				cmd = "reportboardcomment_del asc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("reportboardcommentListColumn", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("reportboardcommentListColumn_sort", st.nextToken());
				response.addCookie(cookie);
			}
			AdminReportboardcommentDAO dao = new AdminReportboardcommentDAO();
			List<ReportboardcommentDTO> reportboardcommentList = dao.findReportboardcommentList(cmd, searchColumn, searchValue, currentPage, pageSize, Long.parseLong(request.getParameter("reportboard_no")));
			String json = new Gson().toJson(reportboardcommentList);
			
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
