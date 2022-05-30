package com.byplace.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.ReportBoardDAO;
import com.byplace.dto.ReportboardcommentDTO;
import com.byplace.dto.UserDTO;



@WebServlet("/reportcdel")
public class ReportCdel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportCdel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//System.out.println(request.getParameter("b_no"));
		//System.out.println(request.getParameter("c_no"));
		//System.out.println(session.getAttribute("m_id"));
		//꼭 검사하세요.
		if(request.getParameter("reportboard_no") != null 
				&& request.getParameter("reportboardcomment_no") != null 
				&& session.getAttribute("USER") != null) {
			//데이터베이스로 보내기
			ReportboardcommentDTO dto = new ReportboardcommentDTO();
			dto.setReportboard_no(Integer.parseInt(request.getParameter("reportboard_no")));
			dto.setReportboardcomment_no(Integer.parseInt(request.getParameter("reportboardcomment_no")));
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			
			ReportBoardDAO dao = new ReportBoardDAO();
			dao.Reportcdel(dto);
			
		}else {
			//System.out.println("에러입니다.");
		}		
		response.sendRedirect("./reportdetail?reportboard_no="
								+ request.getParameter("reportboard_no"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
