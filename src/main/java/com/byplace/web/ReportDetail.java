package com.byplace.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.dao.ReportBoardDAO;
import com.byplace.dto.ReportboardDTO;
import com.byplace.dto.ReportboardcommentDTO;
import com.byplace.dto.UserDTO;
import com.byplace.util.Util;

@WebServlet("/reportdetail")
public class ReportDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportDetail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("USER") != null
				&& (((UserDTO) request.getSession().getAttribute("USER")).getUser_type().equals("관리자")
						|| ((UserDTO) request.getSession().getAttribute("USER")).getUser_no() == Long
								.parseLong(request.getParameter("user_no")))) {
			if (request.getParameter("reportboard_no") != null
					&& Util.str2Int(request.getParameter("reportboard_no"))) {
				int reportboard_no = Integer.parseInt(request.getParameter("reportboard_no"));
				ReportBoardDAO dao = new ReportBoardDAO();
				dao.ReportcountUp(reportboard_no);
				ReportboardDTO detail = dao.Reportdetail(reportboard_no);
				if (detail.getReportboard_commentcount() > 0) {
					List<ReportboardcommentDTO> reportcommList = dao.reportcommentList(reportboard_no);
					request.setAttribute("reportcommList", reportcommList);
				}
				RequestDispatcher rd = request.getRequestDispatcher("/reportdetail.jsp");
				request.setAttribute("reportdetail", detail);
				rd.forward(request, response);

			} else {
				response.sendRedirect("./reportboard");
			}
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
