package com.byplace.admin.web.boardcomment;

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

import com.byplace.admin.dao.AdminBoardcommentDAO;
import com.byplace.dto.BoardcommentDTO;
import com.byplace.dto.UserDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_boardcommentList_JSON")
public class AdminPage_boardcommentList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_boardcommentList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			String searchColumn = "boardcomment_comment";
			if(request.getParameter("searchColumn")!=null && !request.getParameter("searchColumn").equals(""))
				searchColumn = request.getParameter("searchColumn");
			String searchValue = request.getParameter("searchValue");
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			if(sort.equals("boardcomment_no desc"))
				cmd = "boardcomment_no desc";
			else if(sort.equals("boardcomment_no asc"))
				cmd = "boardcomment_no asc";
			else if(sort.equals("user_id desc"))
				cmd = "user_id desc";
			else if(sort.equals("user_id asc"))
				cmd = "user_id asc";
			else if(sort.equals("boardcomment_date desc"))
				cmd = "boardcomment_date desc";
			else if(sort.equals("boardcomment_date asc"))
				cmd = "boardcomment_date asc";
			else if(sort.equals("boardcomment_del desc"))
				cmd = "boardcomment_del desc";
			else
				cmd = "boardcomment_del asc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("boardcommentListColumn", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("boardcommentListColumn_sort", st.nextToken());
				response.addCookie(cookie);
			}
			AdminBoardcommentDAO dao = new AdminBoardcommentDAO();
			List<BoardcommentDTO> boardcommentList = dao.findBoardcommentList(cmd, searchColumn, searchValue, currentPage, pageSize, Long.parseLong(request.getParameter("board_no")));
			String json = new Gson().toJson(boardcommentList);
			
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
