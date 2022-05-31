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
import com.byplace.util.Util;



@WebServlet("/reportdelete")
public class ReportDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("reportboard_no") != null 
				&& session.getAttribute("USER") != null
				&& Util.str2Int(request.getParameter("reportboard_no"))) {
			
			ReportboardDTO dto = new ReportboardDTO();
			dto.setReportboard_no(Integer.parseInt(request.getParameter("reportboard_no")));
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			
			ReportBoardDAO dao = new ReportBoardDAO();
			dao.ReportpostDel(dto);
			response.sendRedirect("./reportboard");
			
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
