package com.byplace.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.BoardDAO;
import com.byplace.dto.BoardcommentDTO;
import com.byplace.dto.UserDTO;



@WebServlet("/cdel")
public class Cdel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cdel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//System.out.println(request.getParameter("b_no"));
		//System.out.println(request.getParameter("c_no"));
		//System.out.println(session.getAttribute("m_id"));
		//꼭 검사하세요.
		if(request.getParameter("board_no") != null 
				&& request.getParameter("boardcomment_no") != null 
				&& session.getAttribute("USER") != null) {
			//데이터베이스로 보내기
			BoardcommentDTO dto = new BoardcommentDTO();
			dto.setBoard_no(Integer.parseInt(request.getParameter("board_no")));
			dto.setBoardcomment_no(Integer.parseInt(request.getParameter("boardcomment_no")));
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			
			BoardDAO dao = new BoardDAO();
			dao.cdel(dto);
			
		}else {
			//System.out.println("에러입니다.");
		}		
		response.sendRedirect("./detail?board_no="
								+ request.getParameter("board_no"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
