package com.byplace.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.NoticeDAO;
import com.byplace.dto.NoticeDTO;
import com.byplace.dto.UserDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/noticeWrite")
public class NoticeWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//여기로 와요
		//response.setCharacterEncoding("UTF-8");
		
		//String title = request.getParameter("title");
		//System.out.println("title : " +  title);
		String url = request.getSession().getServletContext().getRealPath("/noticeImage");//저장위치
		
		MultipartRequest multi = new MultipartRequest(
				//HSR    저장위치  파일크기     파일 중속시 정책
				request, url , 10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		
		String title = multi.getParameter("title");
		String comment = multi.getParameter("comment");
		//파일l
		String file = multi.getFilesystemName("file");
		String oriFileName = multi.getOriginalFileName("file");
		
		//DTO
		NoticeDTO dto = new NoticeDTO();
		dto.setNotice_title(title);
		dto.setNotice_comment(comment);
		dto.setNotice_orifilename(oriFileName);
		dto.setNotice_filename(file);
		HttpSession session = request.getSession();
		dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
		//DAO
		NoticeDAO dao = new NoticeDAO();
		dao.noticeWrite(dto);
		
		response.sendRedirect("./notice");
		
		
	}

}
