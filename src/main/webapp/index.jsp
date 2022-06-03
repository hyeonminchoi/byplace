<%@page import="com.byplace.dto.RestaurantDTO"%>
<%@page import="com.byplace.dao.RestaurantDAO"%>
<%@page import="com.byplace.dto.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
RestaurantDAO dao = new RestaurantDAO();
List<CategoryDTO> categoryList = dao.categorylist();
request.setAttribute("categoryList", categoryList);
List<RestaurantDTO> restaurantList = dao.rList();
request.setAttribute("restaurantList", restaurantList);
List<List<RestaurantDTO>> restaurants = RestaurantDAO.findByCategoryRestaurantList();
request.setAttribute("restaurants", restaurants);
%>
<%@ include file="/favicon.jsp"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<style type="text/css">
.dropbtn {
	background-color: #04aa6d;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: white;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: aqua;
}
.item{
	display: inline-block;
}
.category{
  width: 100%;
  margin: 0 auto;
}
ul.tabs{
  margin: 0px;
  padding: 0px;
  list-style: none;
  height: 60px;
  width: 100%;
}
ul.tabs li{
  background: none;
  color: #222;
  display: inline-block;
  padding: 10px 15px;
  cursor: pointer;
}

ul.tabs li.current{
  background: #ededed;
  color: #222;
}

.tab-content{
  	display: none;
  	border: 1px solid black;
}

.tab-content.current{
	border: 1px solid black;
 	display: block;
 	height: 350px;
	width: 100%;
}
#img{
	display: block;
	min-height: 200px;
	min-width: 200px;
	max-height: 200px;
	max-width: 200px;
	margin: 0 auto;
}
#img img{
	width: 100%;
	height: 200px;
}
</style>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>byplace templete</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS
        ================================================ -->
<!-- Owl Carousel -->
<link rel="stylesheet" href="css/owl.carousel.css">
<!-- bootstrap.min css -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Font-awesome.min css -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- Main Stylesheet -->
<link rel="stylesheet" href="css/animate.min.css">

<link rel="stylesheet" href="css/main.css">
<!-- Responsive Stylesheet -->
<link rel="stylesheet" href="css/responsive.css">
<!-- Js -->
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> -->
<script>
	window.jQuery
			|| document
					.write('<script src="js/vendor/jquery-1.10.2.min.js"><\/script>')
</script>
<script src="js/jquery.nav.js"></script>
<script src="js/jquery.sticky.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/main.js"></script>
</head>
<body>
	<!--
	header-img start 
	============================== -->
	<section id="hero-area">
		<img class="img-responsive" src="./img/kfood3.gif"  width="100%" height="auto"alt="headphoto">
	</section>
	<!--
    Header start 
	============================== -->
	<nav id="navigation">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block" style="background-color: white;">
						<nav class="navbar navbar-default">
							<div class="container-fluid">
								<!-- Brand and toggle get grouped for better mobile display -->
								<div class="navbar-header">
									<a class="navbar-brand" href="./index.jsp"> <img
										src="./img/android-icon-96x96.png" alt="Logo">
									</a>

								</div>

								<!-- Collect the nav links, forms, and other content for toggling -->
								<div class="collapse navbar-collapse"
									id="bs-example-navbar-collapse-1">
									<ul class="nav navbar-nav navbar-right" id="top-nav">
										<li><a href="./index.jsp">Home</a></li>
										<li><a href="./notice">Notice</a></li>
										<li><a href="./board.jsp">Community</a></li>
										<li><a href="./reportboard.jsp">Report</a></li>
										<li><a href="./introduce.jsp">Contacts</a></li>
										<c:if test="${sessionScope.USER eq null }">
											<li class="dropdown"><a href="./login.jsp">
													<button class="dropbtn" href="./login.jsp">Login</button>
													<div class="dropdown-content">
														<a href="./findid.jsp">아이디 찾기</a> <a href="./findpw.jsp">비밀번호
															찾기</a>
													</div></li>
										</c:if>
										<c:if test="${sessionScope.USER ne null }">
											<li class="dropdown"><a href="./logout.jsp">
													<button class="dropbtn" href="./login.jsp">Logout</button>
													<div class="dropdown-content">
														<a href="./userInfo.jsp">개인정보</a> <a href="./findpw.jsp">비밀번호
															찾기</a>
													</div></li>
										</c:if>
									</ul>
								</div>
								<!-- /.navbar-collapse -->
							</div>
							<!-- /.container-fluid -->
						</nav>
					</div>
				</div>
				<!-- .col-md-12 close -->
			</div style="background-color=:white;">
			<!-- .row close -->
		</div>
		<!-- .container close -->
	</nav>
	<!-- header close -->
	<!--
    Slider start
    ============================== -->
	<section id="slider">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block wow fadeInUp" data-wow-duration="500ms"
						data-wow-delay="300ms">
							<ul class="tabs">
								<li class="tab-link current" data-tab="tab-0">전체</li>
								<c:forEach var="i" items="${categoryList}" varStatus="status" >
									<li class="tab-link" data-tab="tab-${status.count}">${i.category_category}</li>
								</c:forEach>
							</ul>
							<div id="tab-0" class="tab-content current owl-example owl-carousel">
									<c:forEach var="i" items="${restaurantList}">
										<div class="item">
											<div id="img">
												<img src="./restaurantImage/${i.restaurant_image}" alt="로드 실패"/><br>
											</div>
											<a href="./restaurantInfo?restaurant_no=${i.restaurant_no}">${i.restaurant_name}</a><br>
											${i.restaurant_roadAddress }, ${i.restaurant_detailAddress }${i.restaurant_extraAddress }<br>
											<br>평점: ${i.restaurant_rating } / 5.0
										</div>
									</c:forEach>
							</div>
							<c:forEach var="j" items="${restaurants}" varStatus="status" >
								<div id="tab-${status.count}" class="tab-content owl-example owl-carousel">
									<c:forEach var="k" items="${j}">
										<div class="item">
											<div id="img">
												<img src="./restaurantImage/${k.restaurant_image}" alt="로드 실패"/><br>
											</div>
											<a href="./restaurantInfo?restaurant_no=${k.restaurant_no}">${k.restaurant_name}</a><br>
											${k.restaurant_roadAddress }, ${k.restaurant_detailAddress }${k.restaurant_extraAddress }<br>
											<br>평점: ${k.restaurant_rating } / 5.0
										</div>
									</c:forEach>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- .col-md-12 close -->
			</div>
			<!-- .row close -->
		</div>
		<!-- .container close -->
	</section>
	<!-- slider close -->
	<!--
    about-us start
    ============================== -->
	<section id="about-us">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block">
						<img class="wow fadeInUp" data-wow-duration="300ms"
							data-wow-delay="400ms" src="img/findu.png" alt="cooker-img">
						<h1 class="heading wow fadeInUp" data-wow-duration="400ms"
							data-wow-delay="500ms">
							Find <span>Your</span> </br> Favorite <span>Restaurant</span>
						</h1>
						<p class="wow fadeInUp" data-wow-duration="300ms"
							data-wow-delay="600ms">
							if u want<br> imagination.<br> delight.<br>
							flavor.<br> together.<br> join us!
						</p>
					</div>
				</div>
				<!-- .col-md-12 close -->
			</div>
			<!-- .row close -->
		</div>
		<!-- .containe close -->
	</section>
	<!-- #call-to-action close -->
	<!--
    blog start
    ============================ -->
	<section id="blog">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block">
						<h1 class="heading">
							We <span>recommend</span> This.
						</h1>
						<ul>
							<li class="wow fadeInLeft" data-wow-duration="300ms"
								data-wow-delay="300ms">
								<div class="blog-img">
									<img src="img/kbbq1.png" alt="blog-img" width="570"
										height="220">
								</div>
								<div class="content-right">
									<h3>Korean BBQ</h3>
									<p>
										Representative<br> of<br> Korean food<br> U<br>
										Have to try it.
									</p>
								</div>
							</li>
							<li class="wow fadeInLeft" data-wow-duration="300ms"
								data-wow-delay="400ms">
								<div class="blog-img">
									<img src="img/spicy1.png" alt="blog-img" width="570"
										height="220">
								</div>
								<div class="content-right">
									<h3>Spicy</h3>
									<p>
										When<br> U<br> stressed<br> try<bR>
										it.
									</p>
								</div>
							</li>
							<li class="wow fadeInLeft" data-wow-duration="300ms"
								data-wow-delay="500ms">
								<div class="content-left">
									<h3>Brunch</h3>
									<p>
										If<br> U<br> want<br> light meal
									</p>
								</div>
								<div class="blog-img-2">
									<img src="img/brunch.png" alt="blog-img" width="570"
										height="220">
								</div>
							</li>
							<li class="wow fadeInLeft" data-wow-duration="300ms"
								data-wow-delay="600ms">
								<div class="content-left">
									<h3>China</h3>
									<p>
										If<br> U<br> want<br> feel china
									</p>
								</div>
								<div class="blog-img-2">
									<img src="img/chfood.png" alt="blog-img" width="570"
										height="220">
								</div>
							</li>
							<li class="wow fadeInLeft" data-wow-duration="300ms"
								data-wow-delay="700ms">
								<div class="blog-img">
									<img src="img/ramen.png" alt="blog-img" width="570"
										height="220">
								</div>
								<div class="content-right">
									<h3>Japan</h3>
									<p>
										If<br> U<br> want<br> feel japan
									</p>
								</div>
							</li>
							<li class="wow fadeInUp" data-wow-duration="300ms"
								data-wow-delay="800ms">
								<div class="blog-img">
									<img src="img/sweet.png" alt="blog-img" width="570"
										height="220">
								</div>
								<div class="content-right">
									<h3>Dessert</h3>
									<p>
										If<br> U<br> want<br> to recover<br> whit
										sweet
									</p>
								</div>
							</li>
						</ul>
						<a class="btn btn-default btn-more-info wow bounceIn"
							data-wow-duration="500ms" data-wow-delay="1200ms" href="#"
							role="button">More Thing</a>
					</div>
				</div>
				<!-- .col-md-12 close -->
			</div>
			<!-- .row close -->
		</div>
		<!-- .containe close -->
	</section>
	<!-- #blog close -->
	<!--
    price start
    ============================ -->
	<section id="commu">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block">
						<h1 class="heading wow fadeInUp" data-wow-duration="300ms"
							data-wow-delay="300ms">
							<span>register</span> your <span>secret</span> restaurant
						</h1>
						<p class="wow fadeInUp" data-wow-duration="300ms"
							data-wow-delay="400ms">Recommend your own restaurant</p>
						<div class="pricing-list">
							<div class="title">
								<h3>
									Let's <span>Go to </span>community
								</h3>
							</div>
							<div>
								<img src="img/group.png" alt="commu">
							</div>
							<a class="btn btn-default pull-right wow bounceIn btn-lg"
								data-wow-duration="500ms" data-wow-delay="1200ms" href="./board"
								role="button">GO!</a>
						</div>
					</div>
				</div>
				<!-- .col-md-12 close -->
			</div>
			<!-- .row close -->
		</div>
		<!-- .containe close -->
	</section>
	<!-- #price close -->
	<!--
   
	<!--
    CONTACT US  start
    ============================= -->
	<section id="contact-us">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block">
						<h1 class="heading wow fadeInUp" data-wow-duration="500ms"
							data-wow-delay="300ms">
							<span>CONTACT US</span>
						</h1>
						<h3 class="title wow fadeInLeft" data-wow-duration="500ms"
							data-wow-delay="300ms">
							Sign Up for <span>Email Alerts</span>
						</h3>
						<form>
							<div class="form-group wow fadeInDown" data-wow-duration="500ms"
								data-wow-delay="600ms">
								<input type="email" class="form-control" id="exampleInputEmail1"
									placeholder="Write your full name here...">
							</div>
							<div class="form-group wow fadeInDown" data-wow-duration="500ms"
								data-wow-delay="800ms">
								<input type="text" class="form-control"
									placeholder="Write your email address here...">
							</div>
							<div class="form-group wow fadeInDown" data-wow-duration="500ms"
								data-wow-delay="1000ms">
								<textarea class="form-control" rows="3"
									placeholder="Write your message here..."></textarea>
							</div>
						</form>
						<a class="btn btn-default wow bounceIn" data-wow-duration="500ms"
							data-wow-delay="1300ms" href="./index,jsp" role="button">send
							message</a>
					</div>
				</div>
				<!-- .col-md-12 close -->
			</div>
			<!-- .row close -->
		</div>
		<!-- .container close -->
	</section>
	<!-- #contact-us close -->
	<!--
    footer  start
    ============================= -->
	<section id="footer">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="block wow fadeInLeft" data-wow-delay="200ms">
						<h3>
							CONTACT <span>INFO</span>
						</h3>
						<div class="info">
							<ul>
								<li>
									<h4>
										<i class="fa fa-phone"></i>Telefone
									</h4>
									<p>(000) 123 456 78- (000) 123 4567 89</p>

								</li>
								<li>
									<h4>
										<i class="fa fa-map-marker"></i>Address
									</h4>
									<p>Bucheon</p>
								</li>
								<li>
									<h4>
										<i class="fa fa-envelope"></i>E mail
									</h4>
									<p>Korea@gmail.com - kor@mail.net</p>

								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- .col-md-4 close -->
				<div class="col-md-4">
					<div class="block wow fadeInLeft" data-wow-delay="700ms">
						<h3>
							BLOG <span> POSTS</span>
						</h3>
						<div class="blog">
							<ul>
								<li>
									<h4>
										<a href="./board">Nov 09-2022</a>
									</h4>
									<p></p>
								</li>
								<br>
								<li>
									<h4>
										<a href="./board">Sep 08-2022</a>
									</h4>
									<p></p>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- .col-md-4 close -->
				<div class="col-md-4">
					<div class="block wow fadeInLeft" data-wow-delay="1100ms">
						<div class="gallary">
							<h3>
								PHOTO <span>STREAM</span>
							</h3>
							<ul>
								<li><a href="./menu.jsp"><img src="img/poto1.png"
										alt="photo" width="100" height="100"></a></li>
								<li><a href="./menu.jsp"><img src="img/poto.png"
										alt="photo" width="100" height="100"></a></li>
								<li><a href="./menu.jsp"><img src="img/deuch.png"
										alt="photo" width="100" height="100"></a></li>
								<li><a href="./menu.jsp"><img src="img/kimchi.png"
										alt="photo" width="100" height="100"></a></li>
							</ul>
						</div>
						<div class="social-media-link">
							<h3>
								Follow <span>US</span>
							</h3>
							<ul>
								<li><a href="#"> <i class="fa fa-twitter">A</i>
								</a></li>
								<li><a href="#"> <i class="fa fa-facebook">B</i>
								</a></li>
								<li><a href="#"> <i class="fa fa-dribbble">C</i>
								</a></li>
								<li><a href="#"> <i class="fa fa-behance">D</i>
								</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- .col-md-4 close -->
			</div>
			<!-- .row close -->
		</div>
		<!-- .containe close -->
	</section>
	<!-- #footer close -->
	<!--
    footer-bottom  start
    ============================= -->
	<footer id="footer-bottom">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="block">
						<p>
						Enjoy your life.
						</p>
					</div>
				</div>
			</div>
		</div>
	</footer>
<script type="text/javascript">
$(document).ready(function(){
    $('ul.tabs li').click(function(){
        var tab_id = $(this).attr('data-tab');

        $('ul.tabs li').removeClass('current');
        $('.tab-content').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    });

});
</script>
</body>
</html>