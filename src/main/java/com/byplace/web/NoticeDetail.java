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



@WebServlet("/noticeDetail")
public class NoticeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("n_no"));
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		//dao에게 일 시키기
		NoticeDAO dao = new NoticeDAO();
		NoticeDTO dto = dao.detail(notice_no);
		
		RequestDispatcher rd = request.getRequestDispatcher("/noticeDetail.jsp");
		request.setAttribute("dto", dto);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
