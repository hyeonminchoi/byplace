package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.ReportBoardDAO;
import com.byplace.dto.ReportboardDTO;
import com.byplace.dto.UserDTO;

@WebServlet("/reportboardWrite")
public class ReportBoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReportBoardWrite() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") !=null) {
			response.sendRedirect("./reportboardWrite.jsp");
		} else { // 로그인이 안 됐을경우
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		if(session.getAttribute("USER") !=null) {
			if(request.getParameter("content") !=null && request.getParameter("title") !=null ) {
				ReportboardDTO dto = new ReportboardDTO();
				dto.setReportboard_title(request.getParameter("title"));
				dto.setReportboard_comment(request.getParameter("content"));
				dto.setUser_no(((UserDTO)session.getAttribute("USER")).getUser_no());
				ReportBoardDAO dao = new ReportBoardDAO();
				dao.Reportboardwrite(dto);
				response.sendRedirect("./reportboard");
			} else { // 제목 or 내용이 없을 경우
				response.sendRedirect("./index.jsp");
			}
		} else { // 로그인이 안 됐을경우
			response.sendRedirect("./index.jsp");
		}
	}

}
