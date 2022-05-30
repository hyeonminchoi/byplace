package com.byplace.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.ReportBoardDAO;
import com.byplace.dto.ReportboardDTO;
import com.byplace.dto.UserDTO;
import com.byplace.util.Util;



@WebServlet("/reportupdate")
public class ReportUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8080/jspProject/update?b_no=8
		if (request.getParameter("reportboard_no") != null) {
			if (Util.str2Int(request.getParameter("reportboard_no"))) {
				int reportboard_no = Integer.parseInt(request.getParameter("reportboard_no"));
				// 세션
				HttpSession session = request.getSession();
				if (session.getAttribute("USER") != null) {
					// bno있고, 숫자이고, mid가 있습니다.
					// 원래 써 있던 글을 가져와야 합니다.
					ReportBoardDAO dao = new ReportBoardDAO();
					ReportboardDTO dto = dao.Reportdetail(reportboard_no);// 어느글
					// 디스패쳐 이동
					RequestDispatcher rd = request.getRequestDispatcher("./reportupdate.jsp");
					request.setAttribute("dto", dto);
					rd.forward(request, response);
				} else {
					response.sendRedirect("./reportindex.jsp");// 로그인하세요.
				}
			} else {
				response.sendRedirect("./reportboard");// 숫자가 아닙니다.
			}
		} else {
			response.sendRedirect("./reportboard");// b_no가 없습니다.
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// 한글
		// b_no, b_title, b_content AND m_id
		HttpSession session = request.getSession();
		if (session.getAttribute("USER") != null) {
			if (request.getParameter("reportboard_no") != null && Util.str2Int(request.getParameter("reportboard_no"))) {
				int reportboard_no = Integer.parseInt(request.getParameter("reportboard_no"));
				String title = request.getParameter("title");
				String comment = request.getParameter("comment");

				ReportboardDTO dto = new ReportboardDTO();
				dto.setReportboard_no(reportboard_no);
				dto.setReportboard_title(Util.HTML2str(title));
				dto.setReportboard_comment(comment);
				dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
				ReportBoardDAO dao = new ReportBoardDAO();
				dao.Reportupdate(dto);
				
				response.sendRedirect("./reportdetail?reportboard_no="+reportboard_no);

			} else {
				response.sendRedirect("./reportboard");// b_no가 이상해요
			}
		} else {
			response.sendRedirect("./index.jsp");// 로그인하세요.
		}

	}

}
