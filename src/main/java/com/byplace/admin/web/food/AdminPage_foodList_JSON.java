package com.byplace.admin.web.food;

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

import com.byplace.admin.dao.AdminFoodDAO;
import com.byplace.dto.FoodDTO;
import com.byplace.dto.UserDTO;
import com.google.gson.Gson;

@WebServlet("/adminPage_foodList_JSON")
public class AdminPage_foodList_JSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPage_foodList_JSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null && ((UserDTO)session.getAttribute("USER")).getUser_type().equals("관리자")) {
			String sort = request.getParameter("sort");
			String cmd = "";
			
			String searchColumn = "food_name";
			if(request.getParameter("searchColumn")!=null && !request.getParameter("searchColumn").equals(""))
				searchColumn = request.getParameter("searchColumn");
			String searchValue = request.getParameter("searchValue");
			String pg = request.getParameter("pg");
			int currentPage = Integer.parseInt(pg);
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			
			if(sort.equals("food_no desc"))
				cmd = "food_no desc";
			else if(sort.equals("food_no asc"))
				cmd = "food_no asc";
			else if(sort.equals("food_name desc"))
				cmd = "food_name desc";
			else if(sort.equals("food_name asc"))
				cmd = "food_name asc";
			else if(sort.equals("food_joined desc"))
				cmd = "food_joined desc";
			else if(sort.equals("food_joined asc"))
				cmd = "food_joined asc";
			else if(sort.equals("food_del desc"))
				cmd = "food_del desc";
			else if(sort.equals("food_del asc"))
				cmd = "food_del asc";
			else if(sort.equals("food_price desc"))
				cmd = "food_price desc";
			else
				cmd = "food_price asc";
			StringTokenizer st = new StringTokenizer(cmd, " ");
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("foodListColumn", st.nextToken());
				response.addCookie(cookie);
			}
			if(st.hasMoreTokens()) {
				Cookie cookie = new Cookie("foodListColumn_sort", st.nextToken());
				response.addCookie(cookie);
			}

			AdminFoodDAO dao = new AdminFoodDAO();
			List<FoodDTO> foodList = dao.findFoodList(cmd, searchColumn, searchValue, currentPage, pageSize, Long.parseLong(request.getParameter("restaurant_no")));
			String json = new Gson().toJson(foodList);
			
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
