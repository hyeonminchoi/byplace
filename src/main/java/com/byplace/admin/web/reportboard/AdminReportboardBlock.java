package com.byplace.admin.web.reportboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.admin.dao.AdminReportboardDAO;

@WebServlet("/adminReportboardBlock")
public class AdminReportboardBlock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminReportboardBlock() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			long reportboard_no = Long.parseLong(request.getParameter("reportboard_no"));
			AdminReportboardDAO adminReportboardDAO = new AdminReportboardDAO();
			int result = adminReportboardDAO.block(reportboard_no);
			if(result==1) { //차단 성공
				writer.println("<script>alert('" + "차단에 성공했습니다" + "'); window.location.href = document.referrer;</script>");
			} else { //차단 실패
				writer.println("<script>alert('" + "차단에 실패했습니다" + "'); window.location.href = document.referrer;</script>");
			}
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
