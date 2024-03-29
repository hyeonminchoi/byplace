<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ include file="/favicon.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>맛집 상세보기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41c04e215b88583eb8be89a445ce294e&libraries=services"></script>
<link href="./css/tabmenu.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<link rel="stylesheet" href="./css/resdetail.css">
<script type="text/javascript">
function menudel(n1, n2){
	if(confirm("삭제하시겠습니까?")){
		location.href = './menudelete?food_no='+n2 + "&restaurant_no=" + n1;
	}
}

function reviewdel(n1, n2){
	if(confirm("삭제하시겠습니까?")){
		location.href='./reviewdelete?review_no='+n1 + "&restaurant_no=" + n2;
	}
}

function reviewup(n1, n2){
	if(confirm("수정하시겠습니까?")){
		location.href='./reviewupdate?review_no='+n1 + "&restaurant_no=" + n2;
	}
}

function resdel(n1){
	if(confirm("삭제하시겠습니까?")){
		location.href='./resdel?restaurant_no='+n1;
	}
}

function resback(){
	location.href='./index.jsp';
}

function resup(n1){
	if(confirm("수정하시겠습니까?")){
		location.href='./resup?restaurant_no='+n1;	
	}
}
$(function(){
$('.starRev span').click(function(){
	  $('#starRev').val($(this).text());
	  $(this).parent().children('span').removeClass('on');
	  $(this).addClass('on').prevAll('span').addClass('on');
	  return false;
	});
});
</script>
<style type="text/css">
#head {
	width: 100%;
	height: 100px;
	background-color: black;
	color: white;
	font-weight: bold;
	text-align: center;
}

#middle {
	width: 100%;
	height: 300px;
	font-size: 20px;
	text-align: center;
}

#content {
	width: 100%;
	height: 500px;
	font-size: 20px;
	text-align: center;
}

.starR1 {
	background:
		url('http://miuu227.godohosting.com/images/icon/ico_review.png')
		no-repeat -52px 0;
	background-size: auto 100%;
	width: 15px;
	height: 30px;
	float: left;
	text-indent: -9999px;
	cursor: pointer;
}

.starR2 {
	background:
		url('http://miuu227.godohosting.com/images/icon/ico_review.png')
		no-repeat right 0;
	background-size: auto 100%;
	width: 15px;
	height: 30px;
	float: left;
	text-indent: -9999px;
	cursor: pointer;
}

.starR1.on {
	background-position: 0 0;
}

.starR2.on {
	background-position: -15px 0;
}

table, td, th {
	border-collapse: collapse;
}
#resup, #resdel, #resback{
	margin-top : 10px;
	height: 30px;
	width: 60px;
    padding: 10px 0px 10px;
    border: 0;
    color: black;
    background-color: #BDBDBD;
    font-size: 15px;
    font-weight: 200;
}
</style>
</head>
<body>
	<div id="head">
		<div id="headoption" style="float: left;">
			<button  id="resback" onclick="resback()">뒤로가기</button>
			<c:if
				test="${sessionScope.USER.user_type eq '사장' or sessionScope.USER.user_type eq '관리자' }">
				<button id="resup" onclick="resup(${resdetail.restaurant_no})">수정</button>
				<button id="resdel" onclick="resdel(${resdetail.restaurant_no})">삭제</button>
			</c:if>
		</div>
		<div id="headtitle" style="text-align: center;">
			<h1>BY PLACE</h1>
		</div>
	</div>
	<div id="middle">
		<div id="resimage" style="float: left;">
			<img alt="" src="./restaurantImage/${resdetail.restaurant_image }"
				style="width: 400px; height: 400px;">
		</div>
		<div id="title" style="text-align: center;">
			점포명<br> ${resdetail.restaurant_name }<br> 주소<br>
			${resdetail.restaurant_postcode }<br>
			${resdetail.restaurant_roadAddress }<br>
			${resdetail.restaurant_detailAddress }<br>
			${resdetail.restaurant_extraAddress }<br>
			────────────────────────────────────────────<br> 평점:
			${resdetail.restaurant_rating }<br> 설명:
			${resdetail.restaurant_description }<br>


		</div>
	</div>
	<div id="content">
		<div class="container" style="float: left;">
			<!-- 탭 메뉴 상단 시작 -->
			<ul class="tabs">
				<li class="tab-link current" data-tab="tab-1">대표메뉴</li>
				<li id="tab-map" class="tab-link" data-tab="tab-2">지도</li>
				<li class="tab-link" data-tab="tab-3">리뷰</li>
				<li class="tab-link" data-tab="tab-4">정보</li>
			</ul>
			<!-- 탭 메뉴 상단 끝 -->
			<!-- 탭 메뉴 내용 시작 -->
			<div id="tab-1" class="tab-content current"
				style="text-align: center;">
				<c:if
					test="${sessionScope.USER.user_type eq '사장' or sessionScope.USER.user_type eq '관리자' }">
					<form action="./menuadd" method="post"
						enctype="multipart/form-data">
						<table id="detailmenu">
							<tr>
								<td><label>메뉴 이름</label></td>
								<td><input type="text" id="food_name"
									placeholder="메뉴 이름을 입력하시오." name="food_name"
									required="required"></td>
								<td></td>
							</tr>
							<tr>
								<td><label>메뉴 설명</label></td>
								<td><input type="text" id="food_description"
									placeholder="메뉴 설명을 입력하시오." name="food_description"
									required="required"></td>
								<td></td>
							</tr>
							<tr>
								<td><label>메뉴 가격</label></td>
								<td><input type="number" id="food_price"
									placeholder="메뉴 가격을 입력하시오." name="food_price"
									required="required"></td>
								<td></td>
							</tr>
							<tr>
								<td><label>메뉴 사진</label></td>
								<td><input type="file" name="food_image" id="food_image"
									accept="image/*" required="required"></td>
								<td><input type="hidden" name="restaurant_no"
									value="${resdetail.restaurant_no }"></td>
								<td></td>
							</tr>
						</table>
						<button id="yes" name="yes">확인</button>
					</form>
					<br>
				</c:if>
				<c:forEach items="${menulist }" var="m">
				<table id="mlist">
					<tr>
						<td><img src="./menuImage/${m.food_image }"
						style="width: 200px; height: 200px;"></td><td></td>
					</tr>
					<tr>	
					<c:if test="${sessionScope.USER.user_type eq '사장' or sessionScope.USER.user_type eq '관리자' }">
						<td><button name="menudel" id="menudel"
							onclick="menudel(${resdetail.restaurant_no},${m.food_no})">삭제</button></td>
							<td></td>
					</c:if>
					</tr>
						<tr>
							<td><label>메뉴 : </label></td>
							<td>${m.food_name }</td>
							<td></td>
						</tr>
						<tr>
							<td><label>가격 : </label></td>
							<td>${m.food_price }</td>
							<td></td>
						</tr>
						<tr>
							<td><label>설명 : </label></td>
							<td>${m.food_description }</td>
							<td></td>
					</table>
				</c:forEach>
			</div>
			<div id="tab-2" class="tab-content">
				<div id="map" style="width: 100%; height: 350px;"></div>
			</div>
			<div id="tab-3" class="tab-content" style="text-align: center;">
				<c:if test="${sessionScope.USER ne null }">
					<form action="./review" method="post">
						<label>리뷰내용</label> <input type="text" id="review_comment"
							placeholder="내용" name="review_comment" required="required"><br>
						<label>리뷰평가</label>
						<div class="starRev" style="margin: auto; text-align: center;">
							<input type="hidden" value="0.5" id="starRev" name="starRev"
								style="margin: auto; text-align: center;"> <span
								class="starR1 on">0.5</span> <span class="starR2">1</span> <span
								class="starR1">1.5</span> <span class="starR2">2</span> <span
								class="starR1">2.5</span> <span class="starR2">3</span> <span
								class="starR1">3.5</span> <span class="starR2">4</span> <span
								class="starR1">4.5</span> <span class="starR2">5</span>
						</div>
						<input type="hidden" name="restaurant_no"
							value="${resdetail.restaurant_no }"> <input type="hidden"
							name="user_no" value="${sessionScope.USER.user_no }">
						<button id="yes">확인</button>
					</form>
				</c:if>
				<table style="margin: auto;">
					<tr
						style="margin-left: auto; margin-right: auto; border-collapse: collapse;">
						<th id=col1>리뷰내용</th>
						<th id=col2>리뷰날짜</th>
						<th id=col3>리뷰평점</th>
						<th id=col4>소비자</th>
					</tr>
					<c:forEach items="${reviewlist }" var="r">
						<tr
							style="margin-left: auto; margin-right: auto; border-collapse: collapse;">
							<th>${r.review_comment }</th>
							<th>${r.review_date }</th>
							<th>${r.review_rating }</th>
							<th>${r.user_id }</th>
							<th>
								<c:if test="${sessionScope.USER.user_id eq r.user_id }">
									<button
										onclick="reviewdel(${r.review_no }, ${resdetail.restaurant_no })">삭제</button>
									<button
										onclick="reviewup(${r.review_no }, ${resdetail.restaurant_no })">수정</button>
								</c:if>
							</th>
						<tr>
					</c:forEach>
				</table>
			</div>
			<div id="tab-4" class="tab-content" style="text-align: center;">
				<c:if
					test="${sessionScope.USER.user_type eq '사장' or sessionScope.USER.user_type eq '관리자' }">
					<form action="./infoadd" method="post">
						<label>사업자 번호</label> <input type="text"
							id="restaurantinfo_businessnumber" placeholder="사업자 번호를 입력하시오."
							name="restaurantinfo_businessnumber" required="required"><br>
						<label>오픈 시간</label> <input type="text"
							id="restaurantinfo_openinghours" placeholder="오픈 시간을 입력하시오."
							name="restaurantinfo_openinghours" required="required"><br>
						<label>음식점 설명</label> <input type="text"
							id="restaurantinfo_description" placeholder="음식점 설명을 입력하시오."
							name="restaurantinfo_description" required="required"><br>
						<input type="hidden" name="restaurant_no"
							value="${resdetail.restaurant_no }">
						<button id="yes">확인</button>
					</form>
					<br>
				</c:if>
				<div id="restaurantinfo" style="text-align: center;">
					사업자 번호 : ${infodetail.restaurantinfo_businessnumber } <br> 오픈
					시간 : ${infodetail.restaurantinfo_openinghours } <br> 음식점 설명 :
					${infodetail.restaurantinfo_description } <br>
				</div>
			</div>
			<!-- 탭 메뉴 내용 끝 -->
		</div>
	</div>
</body>
<script type="text/javascript">

var container = document.getElementById('map');
var options ={
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 3      
};
var map = new kakao.maps.Map(container, options);
$(document).ready(function(){
    $('ul.tabs li').click(function(){
        var tab_id = $(this).attr('data-tab');
        $('ul.tabs li').removeClass('current');
        $('.tab-content').removeClass('current');
        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    });
    
    $('#tab-map').click(function(){
       map.relayout();
        var geocoder = new kakao.maps.services.Geocoder();
        var address='${resdetail.restaurant_roadAddress}';
       geocoder.addressSearch(address, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
               var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
               var marker = new kakao.maps.Marker({
                   map: map,
                   position: coords
               });
               var infowindow = new kakao.maps.InfoWindow({
                   content: '<div style="width:150px;text-align:center;padding:6px 0;"> \
                            ${resdetail.restaurant_name}</div>'
               });
               infowindow.open(map, marker);
               map.setCenter(coords);
           }
       });
    });
});
</script>
</html>