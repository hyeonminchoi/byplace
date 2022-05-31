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
import com.byplace.dto.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/adminBoardEdit")
public class AdminBoardEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminBoardEdit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		String url = session.getServletContext().getRealPath("/restaurantImage");
//		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			long board_no = Long.parseLong(request.getParameter("board_no"));
			String board_title = request.getParameter("board_title");
			String board_comment = request.getParameter("board_comment");
			
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(board_no);
			dto.setBoard_title(board_title);
			dto.setBoard_comment(board_comment);
			
			AdminBoardDAO adminBoardDAO = new AdminBoardDAO();
			int result = adminBoardDAO.edit(dto);
			if(result==1) { //수정 성공
				writer.println("<script>alert('" + "수정에 성공했습니다" + "'); window.location.href = document.referrer;</script>");
			} else { //수정 실패
				writer.println("<script>alert('" + "수정에 실패했습니다" + "'); window.location.href = document.referrer;</script>");
			}
//		} else {
//			response.sendRedirect("./index.jsp");
//		}
	}

}
