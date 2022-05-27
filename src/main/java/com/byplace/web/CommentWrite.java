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
import com.byplace.util.Util;



@WebServlet("/commentWrite")
public class CommentWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null) {
			if(request.getParameter("board_no") != null 
					&& Util.str2Int(request.getParameter("board_no"))) {
				BoardcommentDTO dto = new BoardcommentDTO();
				dto.setBoard_no(Integer.parseInt(request.getParameter("board_no")));
				dto.setBoardcomment_comment(Util.HTML2str(request.getParameter("comment")));;
				//m_id, m_name
				dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
				
				BoardDAO dao = new BoardDAO();
				dao.commentWrite(dto);
				response.sendRedirect("./detail?board_no="+dto.getBoard_no());
				
			}else {
				//숫자가 없거나 변환이 안 됨.
			}
		}else {
			//로그인 하지 않음.
		}
	}

}
