package com.byplace.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.byplace.admin.util.LoginManager;
import com.byplace.dao.UserDAO;
import com.byplace.dto.UserDTO;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("id") != null && request.getParameter("pw") != null) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			UserDTO userDTO = new UserDTO();
			userDTO.setUser_id(id);
			userDTO.setUser_password(pw);

			UserDAO dao = new UserDAO();
			userDTO = dao.login(userDTO);
			
			

			if (userDTO != null) {
				String ip = null;

		        ip = request.getHeader("X-Forwarded-For");
		        
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		            ip = request.getHeader("Proxy-Client-IP"); 
		        } 
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		            ip = request.getHeader("WL-Proxy-Client-IP"); 
		        } 
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		            ip = request.getHeader("HTTP_CLIENT_IP"); 
		        } 
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
		        }
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		            ip = request.getHeader("X-Real-IP"); 
		        }
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		            ip = request.getHeader("X-RealIP"); 
		        }
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		            ip = request.getHeader("REMOTE_ADDR");
		        }
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		            ip = request.getRemoteAddr(); 
		        }
				dao.userlog(userDTO.getUser_no(), "login", ip);
				LoginManager loginManager = LoginManager.getInstance();
				request.getSession().setAttribute("USER", userDTO);
				if(loginManager.isUsing(userDTO.getUser_id())) {
					loginManager.removeSession(userDTO.getUser_id());
				}
				loginManager.setSession(request.getSession(), userDTO.getUser_id());
				response.sendRedirect("./index.jsp");
			} else {
				request.setAttribute("error", "로그인에 실패했습니다.");
				request.getRequestDispatcher("./login.jsp").forward(request, response);
			}
		}
	}

}
