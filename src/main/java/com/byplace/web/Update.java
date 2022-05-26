package com.byplace.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.BoardDAO;
import com.byplace.dto.BoardDTO;
import com.byplace.util.Util;



@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8080/jspProject/update?b_no=8
		if (request.getParameter("board_no") != null) {
			if (Util.str2Int(request.getParameter("board_no"))) {
				int board_no = Integer.parseInt(request.getParameter("board_no"));
				// 세션
				HttpSession session = request.getSession();
				if (session.getAttribute("user_id") != null) {
					// bno있고, 숫자이고, mid가 있습니다.
					// 원래 써 있던 글을 가져와야 합니다.
					BoardDAO dao = new BoardDAO();
					BoardDTO dto = dao.detail(board_no);// 어느글
					// 디스패쳐 이동
					RequestDispatcher rd = request.getRequestDispatcher("./update.jsp");
					request.setAttribute("dto", dto);
					rd.forward(request, response);
				} else {
					response.sendRedirect("./index.jsp");// 로그인하세요.
				}
			} else {
				response.sendRedirect("./board");// 숫자가 아닙니다.
			}
		} else {
			response.sendRedirect("./board");// b_no가 없습니다.
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// 한글
		// b_no, b_title, b_content AND m_id
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") != null) {
			if (request.getParameter("board_no") != null && Util.str2Int(request.getParameter("board_no"))) {
				int board_no = Integer.parseInt(request.getParameter("board_no"));
				String board_title = request.getParameter("board_title");
				String board_comment = request.getParameter("board_comment");

				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(board_no);
				dto.setBoard_title(Util.HTML2str(board_title));
				dto.setBoard_comment(board_comment);
				dto.setUser_id((String) session.getAttribute("user_id"));
				BoardDAO dao = new BoardDAO();
				dao.update(dto);
				
				response.sendRedirect("./detail?board_no="+board_no);

			} else {
				response.sendRedirect("./board");// b_no가 이상해요
			}
		} else {
			response.sendRedirect("./index.jsp");// 로그인하세요.
		}

	}

}
