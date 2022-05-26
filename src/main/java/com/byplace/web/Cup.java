package com.byplace.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.BoardDAO;
import com.byplace.dto.BoardcommentDTO;
import com.byplace.util.Util;



@WebServlet("/cup")
public class Cup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정페이지 호출하기-> 먼저 해당 레코드 값 가져오기
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		BoardcommentDTO dto = new BoardcommentDTO();
		
		if(session.getAttribute("user_id") != null 
				&& request.getParameter("boardcomment_no") != null) {
			//값 담기
			dto.setBoard_no(Integer.parseInt(request.getParameter("board_no")));
			dto.setBoardcomment_no(Integer.parseInt(request.getParameter("boardcomment_no")));
			dto.setUser_id ((String) session.getAttribute("User_id"));
			//DB에 물어보기
			BoardDAO dao = new BoardDAO();
			dto = dao.commentDetail(dto);//만들어주세요.
		}else {
			//값이 안 들어온다면 여기로
		}
		//페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher("./commentUpdate.jsp");
		//값 붙이기
		request.setAttribute("dto", dto);
		rd.forward(request, response);//이동
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정된 내용 저장하기
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user_id") != null 
				&& request.getParameter("boardcomment_no") != null
				&& request.getParameter("boardcomment_comment") != null) {
			//저장하기
			BoardcommentDTO dto = new BoardcommentDTO();
			dto.setBoard_no(Integer.parseInt(request.getParameter("board_no")));
			dto.setBoardcomment_no(Integer.parseInt(request.getParameter("boardcomment_no")));
			dto.setUser_id((String) session.getAttribute("user_id"));
			dto.setBoardcomment_comment(Util.HTML2str(request.getParameter("boardcomment_comment")));
			
			BoardDAO dao = new BoardDAO();
			dao.commentUpdate(dto);
			
		}else {
			
		}
		//detail?b_no=b_no
		response.sendRedirect("./detail?board_no="+request.getParameter("board_no"));
	}

}
