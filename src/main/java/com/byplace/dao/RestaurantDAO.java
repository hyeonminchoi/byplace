package com.byplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.byplace.db.DBConnection;
import com.byplace.dto.CategoryDTO;
import com.byplace.dto.FoodDTO;
import com.byplace.dto.RestaurantDTO;
import com.byplace.dto.RestaurantinfoDTO;
import com.byplace.dto.ReviewDTO;

public class RestaurantDAO {

	public RestaurantDTO restboard(RestaurantDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO restaurant(restaurant_name, restaurant_description, restaurant_postcode, restaurant_roadAddress, restaurant_detailAddress, restaurant_extraAddress, category_category, restaurant_image, user_no) VALUES(?, ?, ?, ?, ?, ?, ?, ?, (SELECT user_no FROM user WHERE user_id=?)) ";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRestaurant_name());
			pstmt.setString(2, dto.getRestaurant_description());
			pstmt.setString(3, dto.getRestaurant_postcode());
			pstmt.setString(4, dto.getRestaurant_roadAddress());
			pstmt.setString(5, dto.getRestaurant_detailAddress());
			pstmt.setString(6, dto.getRestaurant_extraAddress());
			pstmt.setString(7, dto.getCategory_category());
			pstmt.setString(8, dto.getRestaurant_image());
			pstmt.setString(9, dto.getUser_id());

			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public List<RestaurantDTO> rList() {
		List<RestaurantDTO> reslist = new ArrayList<RestaurantDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM restaurantView";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RestaurantDTO dto = new RestaurantDTO();
				dto.setRestaurant_no(rs.getLong("restaurant_no"));
				dto.setRestaurant_name(rs.getString("restaurant_name"));
				dto.setRestaurant_description(rs.getString("restaurant_description"));
				dto.setRestaurant_roadAddress(rs.getString("restaurant_roadAddress"));
				dto.setRestaurant_detailAddress(rs.getString("restaurant_detailAddress"));
				dto.setRestaurant_extraAddress(rs.getString("restaurant_extraAddress"));
				dto.setRestaurant_image(rs.getString("restaurant_image"));
				dto.setRestaurant_rating(rs.getDouble("restaurant_rating"));
				reslist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reslist;
	}

	public RestaurantDTO resdetail(long restaurant_no) {
		RestaurantDTO dto = new RestaurantDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM restaurant WHERE restaurant_no=?";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, restaurant_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setRestaurant_no(rs.getLong("restaurant_no"));
				dto.setRestaurant_name(rs.getString("restaurant_name"));
				dto.setRestaurant_image(rs.getString("restaurant_image"));
				dto.setRestaurant_description(rs.getString("restaurant_description"));
				dto.setRestaurant_postcode(rs.getString("restaurant_postcode"));
				dto.setRestaurant_roadAddress(rs.getString("restaurant_roadAddress"));
				dto.setRestaurant_detailAddress(rs.getString("restaurant_detailAddress"));
				dto.setRestaurant_extraAddress(rs.getString("restaurant_extraAddress"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public List<CategoryDTO> categorylist() {
		List<CategoryDTO> catelist = new ArrayList<CategoryDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM category";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCategory_category(rs.getString("category_category"));
				catelist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return catelist;
	}

	public void menuadd(FoodDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO food (restaurant_no, food_name, food_price, food_image, food_description) VALUES (? ,?, ?, ?, ?)";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getRestaurant_no());
			pstmt.setString(2, dto.getFood_name());
			pstmt.setInt(3, dto.getFood_price());
			pstmt.setString(4, dto.getFood_image());
			pstmt.setString(5, dto.getFood_description());
			pstmt.setString(6, dto.getUser_id());

			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<FoodDTO> menulist(long restaurant_no) {
		List<FoodDTO> list = new ArrayList<FoodDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM food WHERE restaurant_no=? AND food_del = 0";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, restaurant_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FoodDTO dto = new FoodDTO();
				dto.setFood_name(rs.getString("food_name"));
				dto.setFood_description(rs.getString("food_description"));
				dto.setFood_price(rs.getInt("food_price"));
				dto.setFood_image(rs.getString("food_image"));
				dto.setRestaurant_no(rs.getLong("restaurant_no"));
				dto.setFood_no(rs.getLong("food_no"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void menudelete(FoodDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE food SET food_del=1 WHERE food_no=?";

		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getFood_no());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static List<List<RestaurantDTO>> findByCategoryRestaurantList() {
		RestaurantDAO dao = new RestaurantDAO();
		List<CategoryDTO> categoryList = dao.categorylist();
		List<List<RestaurantDTO>> restaurantList = new ArrayList<>();
		for (int i = 0; i < categoryList.size(); i++) {
			List<RestaurantDTO> list = new ArrayList<>();
			String sql = "SELECT * FROM restaurantView WHERE category_category=? ORDER BY restaurant_rating DESC";
			try {
				Connection connection = DBConnection.dbConn();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, categoryList.get(i).getCategory_category());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					RestaurantDTO restaurant = new RestaurantDTO();
					restaurant.setRestaurant_no(rs.getLong("restaurant_no"));
					restaurant.setRestaurant_joined(rs.getString("restaurant_joined"));
					restaurant.setRestaurant_postcode(rs.getString("restaurant_postcode"));
					restaurant.setRestaurant_roadAddress(rs.getString("restaurant_roadAddress"));
					restaurant.setRestaurant_detailAddress(rs.getString("restaurant_detailAddress"));
					restaurant.setRestaurant_extraAddress(rs.getString("restaurant_extraAddress"));
					restaurant.setCategory_category(rs.getString("category_category"));
					restaurant.setRestaurant_description(rs.getString("restaurant_description"));
					restaurant.setRestaurant_image(rs.getString("restaurant_image"));
					restaurant.setRestaurant_name(rs.getString("restaurant_name"));
					restaurant.setRestaurant_rating(rs.getDouble("restaurant_rating"));
					restaurant.setUser_no(rs.getLong("user_no"));

					list.add(restaurant);
				}
				restaurantList.add(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return restaurantList;
	}

	public List<ReviewDTO> reviewlist(long restaurant_no) {
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reviewview WHERE restaurant_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, restaurant_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_no(rs.getLong("review_no"));
				dto.setReview_comment(rs.getString("review_comment"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_rating(rs.getDouble("review_rating"));
				dto.setRestaurant_no(rs.getLong("restaurant_no"));
				dto.setUser_id(rs.getString("user_id"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public void infoadd(RestaurantinfoDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO restaurantinfo (restaurantinfo_businessnumber, restaurantinfo_openinghours, restaurantinfo_description, restaurant_no) VALUES (?, ?, ?, ?)";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRestaurantinfo_businessnumber());
			pstmt.setString(2, dto.getRestaurantinfo_openinghours());
			pstmt.setString(3, dto.getRestaurantinfo_description());
			pstmt.setLong(4, dto.getRestaurant_no());
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public RestaurantinfoDTO infodetail(long restaurant_no) {
		RestaurantinfoDTO dto = new RestaurantinfoDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM restaurantinfo WHERE restaurant_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, restaurant_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setRestaurantinfo_no(rs.getLong("restaurantinfo_no"));
				dto.setRestaurantinfo_businessnumber(rs.getString("restaurantinfo_businessnumber"));
				dto.setRestaurantinfo_openinghours(rs.getString("restaurantinfo_openinghours"));
				dto.setRestaurantinfo_description(rs.getString("restaurantinfo_description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public void reviewdelete(ReviewDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE review SET review_del=1 WHERE review_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getReview_no());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void resdel(RestaurantDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE restaurant SET restaurant_del=1 WHERE restaurant_no=?";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dto.getRestaurant_no());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void resup(RestaurantDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE restaurant SET restaurant_name=?, restaurant_description=?, restaurant_postcode=?, restaurant_roadAddress=?, restaurant_detailAddress=?, restaurant_extraAddress=?, category_category=?, restaurant_image=? WHERE restaurant_no=?";
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRestaurant_name());
			pstmt.setString(2, dto.getRestaurant_description());
			pstmt.setString(3, dto.getRestaurant_postcode());
			pstmt.setString(4, dto.getRestaurant_roadAddress());
			pstmt.setString(5, dto.getRestaurant_detailAddress());
			pstmt.setString(6, dto.getRestaurant_extraAddress());
			pstmt.setString(7, dto.getCategory_category());
			pstmt.setString(8, dto.getRestaurant_image());
			pstmt.setLong(9, dto.getRestaurant_no());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void review(ReviewDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO review (review_comment,  review_rating, user_no, restaurant_no) VALUES (?, ?, ?, ?)";
		
		try {
			con = DBConnection.dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getReview_comment());
			pstmt.setDouble(2, dto.getReview_rating());
			pstmt.setLong(3, dto.getUser_no());
			pstmt.setLong(4, dto.getRestaurant_no());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
