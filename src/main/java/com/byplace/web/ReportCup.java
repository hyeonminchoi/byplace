package com.byplace.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.ReportBoardDAO;
import com.byplace.dto.ReportboardcommentDTO;
import com.byplace.dto.UserDTO;
import com.byplace.util.Util;



@WebServlet("/reportcup")
public class ReportCup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportCup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정페이지 호출하기-> 먼저 해당 레코드 값 가져오기
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		ReportboardcommentDTO dto = new ReportboardcommentDTO();
		
		if(session.getAttribute("USER") != null 
				&& request.getParameter("reportboardcomment_no") != null) {
			//값 담기
			dto.setReportboard_no(Integer.parseInt(request.getParameter("reportboard_no")));
			dto.setReportboardcomment_no(Integer.parseInt(request.getParameter("reportboardcomment_no")));
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			//DB에 물어보기
			ReportBoardDAO dao = new ReportBoardDAO();
			dto = dao.ReportcommentDetail(dto);//만들어주세요.
		}else {
			//값이 안 들어온다면 여기로
		}
		//페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher("./reportcommentUpdate.jsp");
		//값 붙이기
		request.setAttribute("dto", dto);
		rd.forward(request, response);//이동
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정된 내용 저장하기
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("USER") != null 
				&& request.getParameter("reportboardcomment_no") != null
				&& request.getParameter("comment") != null) {
			//저장하기
			ReportboardcommentDTO dto = new ReportboardcommentDTO();
			dto.setReportboard_no(Integer.parseInt(request.getParameter("reportboard_no")));
			dto.setReportboardcomment_no(Integer.parseInt(request.getParameter("reportboardcomment_no")));
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			dto.setReportboardcomment_comment(Util.HTML2str(request.getParameter("comment")));
			
			ReportBoardDAO dao = new ReportBoardDAO();
			dao.ReportcommentUpdate(dto);
			
		}else {
			
		}
		//detail?b_no=b_no
		response.sendRedirect("./reportdetail?reportboard_no="+request.getParameter("reportboard_no"));
	}

}
