package com.byplace.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.BoardDAO;
import com.byplace.dto.BoardDTO;
import com.byplace.dto.UserDTO;
import com.byplace.util.Util;



@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("board_no") != null 
				&& session.getAttribute("USER") != null
				&& Util.str2Int(request.getParameter("board_no"))) {
			
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(Integer.parseInt(request.getParameter("board_no")));
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			
			BoardDAO dao = new BoardDAO();
			dao.postDel(dto);
			response.sendRedirect("./board");
			
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
