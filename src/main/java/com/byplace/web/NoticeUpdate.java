package com.byplace.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.NoticeDAO;
import com.byplace.dao.ReportBoardDAO;
import com.byplace.dto.NoticeDTO;
import com.byplace.dto.ReportboardDTO;
import com.byplace.dto.UserDTO;
import com.byplace.util.Util;



@WebServlet("/noticeupdate")
public class NoticeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NoticeUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8080/jspProject/update?b_no=8
		if (request.getParameter("notice_no") != null) {
			if (Util.str2Int(request.getParameter("notice_no"))) {
				int notice_no = Integer.parseInt(request.getParameter("notice_no"));
				// 세션
				HttpSession session = request.getSession();
				if (session.getAttribute("USER") != null) {
					// bno있고, 숫자이고, mid가 있습니다.
					// 원래 써 있던 글을 가져와야 합니다.
					NoticeDAO dao = new NoticeDAO();
					NoticeDTO dto = dao.detail(notice_no);// 어느글
					// 디스패쳐 이동
					RequestDispatcher rd = request.getRequestDispatcher("./noticeupdate.jsp");
					request.setAttribute("dto", dto);
					rd.forward(request, response);
				} else {
					response.sendRedirect("./index.jsp");// 로그인하세요.
				}
			} else {
				response.sendRedirect("./notice");// 숫자가 아닙니다.
			}
		} else {
			response.sendRedirect("./notice");// b_no가 없습니다.
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// 한글
		// b_no, b_title, b_content AND m_id
		HttpSession session = request.getSession();
		if (session.getAttribute("USER") != null) {
			if (request.getParameter("notice_no") != null && Util.str2Int(request.getParameter("notice_no"))) {
				int notice_no = Integer.parseInt(request.getParameter("notice_no"));
				String title = request.getParameter("title");
				String comment = request.getParameter("comment");

				NoticeDTO dto = new NoticeDTO();
				dto.setNotice_no(notice_no);
				dto.setNotice_title(Util.HTML2str(title));
				dto.setNotice_comment(comment);
				dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
				NoticeDAO dao = new NoticeDAO();
				dao.noticeupdate(dto);
				
				response.sendRedirect("./noticeDetail?notice_no="+notice_no);

			} else {
				response.sendRedirect("./notice");// b_no가 이상해요
			}
		} else {
			response.sendRedirect("./index.jsp");// 로그인하세요.
		}

	}

}
