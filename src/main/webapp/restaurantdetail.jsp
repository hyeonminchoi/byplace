<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음식점 상세보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=41c04e215b88583eb8be89a445ce294e&libraries=services"></script>
<link href="./css/tabmenu.css" rel="stylesheet">
<script type="text/javascript">
$(function(){
	$('#menuup').click(function(){
		location.href = './index.jsp';
	});
});

$(function(){
	$('#menudel').click(function(){
		if(confirm("삭제하시겠습니까?")){
			location.href = './menudelete';
		}
	});
});
</script>
<style type="text/css">
#head {
	width: 100%;
	height: 100px;
}

#middle {
	width: 100%;
	height: 300px;
}

#content {
	width: 100%;
	height: 450px;
}
</style>
</head>
<body>
	<div id="head">
		<div id="headoption" style="float: left;">
		<button onclick="back">뒤로가기</button>
		<button onclick="update">수정</button>
		<button onclick="delete">삭제</button>
		</div>
		<div id="headtitle" style="text-align: center;">
			<h1>BY PLACE</h1>
		</div>
	</div>
	<div id="middle">
		<div id="resimage" style="float: left;">
		<img alt="${resdetail.restaurant_name }" src="./restaurantImage/${resdetail.restaurant_image }">
		</div>
		<div id="title" style="text-align: center;">
		${resdetail.restaurant_name }<br>
		${resdetail.restaurant_postcode }<br>
		${resdetail.restaurant_roadAddress }<br>
		${resdetail.restaurant_detailAddress }<br>
		${resdetail.restaurant_extraAddress }<br>
		────────────────────────────────────────────<br>
		${resdetail.restaurant_description }
		
		
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
			<div id="tab-1" class="tab-content current">
			<c:if test="${sessionScope.USER ne null }">
			<form action="./menuadd" method="post" enctype="multipart/form-data">
			<label>음식 이름</label>
			<input type="text" id="food_name" placeholder="음식 이름을 입력하시오." name="food_name" required="required"><br>
			<label>음식 설명</label>
			<input type="text" id="food_description" placeholder="음식 설명을 입력하시오." name="food_description" required="required"><br>
			<label>음식 가격</label>
			<input type="number" id="food_price" placeholder="음식 가격을 입력하시오." name="food_price" required="required"><br>
			<label>음식 사진</label>
			<input type="file" name="food_image" id="food_image" accept="image/*" required="required"><br>
			<input type="hidden" name="restaurant_no" value="${resdetail.restaurant_no }">
			<button>확인</button>
			</form>
			<button name="cancel" id="cancel">취소</button><br>
			</c:if>
			<c:forEach items="${menulist }" var="m">
				<img src="./menuImage/${m.food_image }">
				<c:if test="${sessionScope.USER ne null }">
				<button name="menuup" id="menuup">수정</button>
				<button name="menudel" id="menudel">삭제</button>
				</c:if>
				 <br>
				${m.food_name } <br>
				${m.food_price } <br>
				${m.food_description } <br>
			</c:forEach>
			</div>
			<div id="tab-2" class="tab-content">
				<div id="map" style="width:100%;height:350px;"></div>
			</div>
			<div id="tab-3" class="tab-content">
			
			</div>
			<div id="tab-4" class="tab-content">
			
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