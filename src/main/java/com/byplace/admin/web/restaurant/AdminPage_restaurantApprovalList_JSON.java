package com.byplace.admin.web.restaurant;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.admin.dao.AdminRestaurantDAO;
import com.byplace.dto.RestaurantDTO;
import com.byplace.dto.UserDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_restaurantApprovalList_JSON")
public class AdminPage_restaurantApprovalList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_restaurantApprovalList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			String searchColumn = "restaurant_name";
			if(request.getParameter("searchColumn")!=null && !request.getParameter("searchColumn").equals(""))
				searchColumn = request.getParameter("searchColumn");
			String searchValue = request.getParameter("searchValue");
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			if(sort.equals("restaurant_no asc"))
				cmd = "restaurant_no asc";
			else if(sort.equals("restaurant_no desc"))
				cmd = "restaurant_no desc";
			else if(sort.equals("restaurant_name asc"))
				cmd = "restaurant_name asc";
			else if(sort.equals("restaurant_name desc"))
				cmd = "restaurant_name desc";
			else if(sort.equals("category_category asc"))
				cmd = "category_category asc";
			else if(sort.equals("category_category desc"))
				cmd = "category_category desc";
			else if(sort.equals("restaurant_joined asc"))
				cmd = "restaurant_joined asc";
			else if(sort.equals("restaurant_joined desc"))
				cmd = "restaurant_joined desc";
			else if(sort.equals("restaurant_del asc"))
				cmd = "restaurant_del asc";
			else if(sort.equals("restaurant_del desc"))
				cmd = "restaurant_del desc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("restaurantApprovalListColumn", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("restaurantApprovalListColumn_sort", st.nextToken());
				response.addCookie(cookie);
			}
			
			AdminRestaurantDAO restaurantDAO = new AdminRestaurantDAO();
			List<RestaurantDTO> restaurantList = restaurantDAO.findByApprovalList(cmd, searchColumn, searchValue, currentPage, pageSize);
			String json = new Gson().toJson(restaurantList);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} else {
			response.sendRedirect("./index.jsp");
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./adminPage.jsp");
	}

}
