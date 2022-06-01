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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/adminNoticeImageEdit")
public class AdminNoticeImageEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminNoticeImageEdit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		String url = session.getServletContext().getRealPath("/noticeImage");
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			MultipartRequest multipartRequest = new MultipartRequest(
					request, url, 10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			long notice_no = Long.parseLong(multipartRequest.getParameter("notice_no"));
			String notice_filename = multipartRequest.getFilesystemName("notice_image");
			String notice_oriFileName = multipartRequest.getOriginalFileName("notice_image");
			
			NoticeDTO dto = new NoticeDTO();
			dto.setNotice_no(notice_no);
			dto.setNotice_orifilename(notice_oriFileName);
			dto.setNotice_filename(notice_filename);
			
			AdminNoticeDAO adminNoticeDAO = new AdminNoticeDAO();
			int result = adminNoticeDAO.imageEdit(dto);
			if(result==1) { //추가 성공
				writer.println("<script>alert('" + "수정에 성공했습니다" + "'); window.location.href = document.referrer;</script>");
			} else { //추가 실패
				writer.println("<script>alert('" + "수정에 실패했습니다" + "'); window.location.href = document.referrer;</script>");
			}
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

}
