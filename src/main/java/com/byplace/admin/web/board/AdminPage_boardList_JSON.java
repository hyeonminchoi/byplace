package com.byplace.admin.web.board;

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

import com.byplace.admin.dao.AdminBoardDAO;
import com.byplace.dto.BoardDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_boardList_JSON")
public class AdminPage_boardList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_boardList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			String searchColumn = "board_title";
			if(request.getParameter("searchColumn")!=null && !request.getParameter("searchColumn").equals(""))
				searchColumn = request.getParameter("searchColumn");
			String searchValue = request.getParameter("searchValue");
			
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			if(sort.equals("board_no asc"))
				cmd = "board_no asc";
			else if(sort.equals("board_no desc"))
				cmd = "board_no desc";
			else if(sort.equals("board_title asc"))
				cmd = "board_title asc";
			else if(sort.equals("board_title desc"))
				cmd = "board_title desc";
			else if(sort.equals("board_date asc"))
				cmd = "board_date asc";
			else if(sort.equals("board_date desc"))
				cmd = "board_date desc";
			else if(sort.equals("board_count asc"))
				cmd = "board_count asc";
			else if(sort.equals("board_count desc"))
				cmd = "board_count desc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("boardListColumn", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("boardListColumn_sort", st.nextToken());
				response.addCookie(cookie);
			}
			
			AdminBoardDAO boardDAO = new AdminBoardDAO();
			List<BoardDTO> boardList = boardDAO.findAll(cmd, searchColumn, searchValue, currentPage, pageSize);
			String json = new Gson().toJson(boardList);
			
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
