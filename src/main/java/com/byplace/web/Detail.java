package com.byplace.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.BoardDAO;
import com.byplace.dto.BoardDTO;
import com.byplace.dto.BoardcommentDTO;
import com.byplace.util.Util;

@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Detail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("board_no") != null && Util.str2Int(request.getParameter("board_no"))) {
			long board_no = Integer.parseInt(request.getParameter("board_no"));
			BoardDAO dao = new BoardDAO();
			dao.countUp(board_no);
			BoardDTO detail = dao.detail(board_no);
			if (detail.getBoard_commentcount() > 0) {
				List<BoardcommentDTO> commList = dao.commentList(board_no);
				request.setAttribute("commList", commList);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
			request.setAttribute("detail", detail);
			rd.forward(request, response);

		} else {
			response.sendRedirect("./board");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
