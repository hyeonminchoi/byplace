package com.byplace.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.byplace.dao.RestaurantDAO;
import com.byplace.dto.FoodDTO;
import com.byplace.dto.UserDTO;
import com.byplace.util.Util;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/menuadd")
public class Menuadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Menuadd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurantDAO dao = new RestaurantDAO();
		List<FoodDTO> menulist = dao.menulist();
		RequestDispatcher rd = request.getRequestDispatcher("./restaurantdetail.jsp");
		request.setAttribute("menulist", menulist);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null
				&& request.getParameter("restaurant_no") != null
				&& Util.str2Int(request.getParameter("restaurant_no"))) {
			String url = session.getServletContext().getRealPath("/restaurantImage");
			System.out.println(url);
			MultipartRequest mr = new MultipartRequest(
					request, url, 10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			String food_name = mr.getFilesystemName("food_name");
			String food_description = mr.getFilesystemName("food_description");
			String food_image = mr.getFilesystemName("food_image");
			
			FoodDTO dto = new FoodDTO();
			dto.setFood_name(food_name);
			dto.setFood_description(food_description);
			dto.setFood_price(Integer.parseInt(request.getParameter("food_price")));
			dto.setFood_image(food_image);
			dto.setUser_id(((UserDTO)session.getAttribute("USER")).getUser_id());
			RestaurantDAO dao = new RestaurantDAO();
			dao.menuadd(dto);
			
			response.sendRedirect("./index.jsp");
		} else {
			response.sendRedirect("./login.jsp");
		}
	}

}
