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



@WebServlet("/reportboard")
public class ReportBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportBoard() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int pageNo = 1;
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		
		ReportBoardDAO dao = new ReportBoardDAO();
		List<ReportboardDTO> reportboardList = dao.ReportboardList(pageNo * 10 - 10);
		
		RequestDispatcher rd = request.getRequestDispatcher("./reportboard.jsp");
		request.setAttribute("list", reportboardList);
		
		if(reportboardList.size()!=0)
			request.setAttribute("totalcount", reportboardList.get(0).getTotalcount());
		else
			request.setAttribute("totalcount", 0);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
