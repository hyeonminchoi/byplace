package com.byplace.admin.web.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.admin.dao.AdminBoardDAO;
import com.byplace.dto.UserDTO;

@WebServlet("/adminBoardDelete")
public class AdminBoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminBoardDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			long board_no = Long.parseLong(request.getParameter("board_no"));
			AdminBoardDAO adminBoardDAO = new AdminBoardDAO();
			int result = adminBoardDAO.delete(board_no);
			if(result==1) { //삭제 성공
				writer.println("<script>alert('" + "삭제에 성공했습니다" + "'); window.location.href = document.referrer;</script>");
			} else { //삭제 실패
				writer.println("<script>alert('" + "삭제에 실패했습니다" + "'); window.location.href = document.referrer;</script>");
			}
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

}
