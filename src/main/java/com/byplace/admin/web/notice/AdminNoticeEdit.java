package com.byplace.admin.web.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.admin.dao.AdminNoticeDAO;
import com.byplace.dto.NoticeDTO;
import com.byplace.dto.UserDTO;

@WebServlet("/adminNoticeEdit")
public class AdminNoticeEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminNoticeEdit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			long notice_no = Long.parseLong(request.getParameter("notice_no"));
			String notice_title = request.getParameter("notice_title");
			String notice_comment = request.getParameter("notice_comment");
			
			NoticeDTO dto = new NoticeDTO();
			dto.setNotice_no(notice_no);
			dto.setNotice_title(notice_title);
			dto.setNotice_comment(notice_comment);
			
			AdminNoticeDAO adminNoticeDAO = new AdminNoticeDAO();
			int result = adminNoticeDAO.edit(dto);
			if(result==1) { //수정 성공
				writer.println("<script>alert('" + "수정에 성공했습니다" + "'); window.location.href = document.referrer;</script>");
			} else { //수정 실패
				writer.println("<script>alert('" + "수정에 실패했습니다" + "'); window.location.href = document.referrer;</script>");
			}
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

}
