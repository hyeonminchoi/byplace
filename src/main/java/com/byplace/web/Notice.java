package com.byplace.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.NoticeDAO;
import com.byplace.dto.NoticeDTO;

@WebServlet("/notice")
public class Notice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Notice() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int pageNo = 1;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}

		NoticeDAO dao = new NoticeDAO();
		List<NoticeDTO> list = dao.noticeList(pageNo * 10 - 10);
		RequestDispatcher rd = request.getRequestDispatcher("./notice.jsp");
		request.setAttribute("list", list);

		if (list.size() != 0)
			request.setAttribute("totalcount", list.get(0).getTotalcount());
		else
			request.setAttribute("totalcount", 0);
		request.setAttribute("pageNo", pageNo);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
