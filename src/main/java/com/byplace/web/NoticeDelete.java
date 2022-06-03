package com.byplace.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.NoticeDAO;
import com.byplace.dto.NoticeDTO;
import com.byplace.dto.UserDTO;
import com.byplace.util.Util;



@WebServlet("/noticedelete")
public class NoticeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("notice_no") != null 
				&& session.getAttribute("USER") != null
				&& Util.str2Int(request.getParameter("notice_no"))) {
			
			NoticeDTO dto = new NoticeDTO();
			dto.setNotice_no(Integer.parseInt(request.getParameter("notice_no")));
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			
			NoticeDAO dao = new NoticeDAO();
			dao.noticepostDel(dto);
			response.sendRedirect("./notice");
			
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
