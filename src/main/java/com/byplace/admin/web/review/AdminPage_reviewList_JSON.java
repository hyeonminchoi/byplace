package com.byplace.admin.web.review;

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

import com.byplace.admin.dao.AdminReviewDAO;
import com.byplace.dto.ReviewDTO;
import com.byplace.dto.UserDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_reviewList_JSON")
public class AdminPage_reviewList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_reviewList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			
			String searchColumn = "review_comment";
			if(request.getParameter("searchColumn")!=null && !request.getParameter("searchColumn").equals(""))
				searchColumn = request.getParameter("searchColumn");
			String searchValue = request.getParameter("searchValue");
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			
			if(sort.equals("review_no desc"))
				cmd = "review_no desc";
			else if(sort.equals("review_no asc"))
				cmd = "review_no asc";
			if(sort.equals("user_id desc"))
				cmd = "user_id desc";
			else if(sort.equals("user_id asc"))
				cmd = "user_id asc";
			if(sort.equals("review_date desc"))
				cmd = "review_date desc";
			else if(sort.equals("review_date asc"))
				cmd = "review_date asc";
			if(sort.equals("review_rating desc"))
				cmd = "review_rating desc";
			else if(sort.equals("review_rating asc"))
				cmd = "review_rating asc";
			if(sort.equals("review_del desc"))
				cmd = "review_del desc";
			else
				cmd = "review_del asc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("reviewListColumn", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("reviewListColumn_sort", st.nextToken());
				response.addCookie(cookie);
			}

			AdminReviewDAO dao = new AdminReviewDAO();
			List<ReviewDTO> reviewList = dao.findReviewList(cmd, searchColumn, searchValue, currentPage, pageSize, Long.parseLong(request.getParameter("restaurant_no")));
			String json = new Gson().toJson(reviewList);
			
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
