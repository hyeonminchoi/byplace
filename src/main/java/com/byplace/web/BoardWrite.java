package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.BoardDAO;
import com.byplace.dto.BoardDTO;
import com.byplace.dto.UserDTO;

@WebServlet("/boardWrite")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardWrite() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") !=null) {
			response.sendRedirect("./boardWrite.jsp");
		} else { // 로그인이 안 됐을경우
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		if(session.getAttribute("USER") !=null) {
			if(request.getParameter("comment") !=null && request.getParameter("title") !=null ) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_title(request.getParameter("title"));
				dto.setBoard_comment(request.getParameter("comment"));
				dto.setUser_no(((UserDTO)session.getAttribute("USER")).getUser_no());
				BoardDAO dao = new BoardDAO();
				dao.boardwrite(dto);
				response.sendRedirect("./board");
			} else { // 제목 or 내용이 없을 경우
				response.sendRedirect("./index.jsp");
			}
		} else { // 로그인이 안 됐을경우
			response.sendRedirect("./index.jsp");
		}
	}

}
