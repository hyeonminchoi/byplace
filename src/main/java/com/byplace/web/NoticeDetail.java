package com.byplace.web;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.NoticeDAO;
import com.byplace.dto.NoticeDTO;
import com.byplace.dto.UserDTO;
import com.byplace.util.Util;



@WebServlet("/noticeDetail")
public class NoticeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			if (request.getParameter("notice_no") != null
					&& Util.str2Int(request.getParameter("notice_no"))) {
				int notice_no = Integer.parseInt(request.getParameter("notice_no"));
				NoticeDAO dao = new NoticeDAO();
				NoticeDTO detail = dao.detail(notice_no);
				RequestDispatcher rd = request.getRequestDispatcher("/noticeDetail.jsp");
				request.setAttribute("noticedetail", detail);
				rd.forward(request, response);

			} else {
				response.sendRedirect("./notice");
			}
		} 
	}

