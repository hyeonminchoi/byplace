<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음식점 상세보기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="./css/tabmenu.css" rel="stylesheet">
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
		<button>뒤로가기</button>
		<button>수정</button>
		<button>삭제</button>
		</div>
		<div id="headtitle" style="text-align: center;">
			<h1>BY PLACE</h1>
		</div>
	</div>
	<div id="middle">
		<div id="resimage" style="float: left;">음식점사진</div>
		<div id="title">음식점이름</div>
	</div>
	<div id="content">
		<div class="container">
			<!-- 탭 메뉴 상단 시작 -->
			<ul class="tabs">
				<li class="tab-link current" data-tab="tab-1">대표메뉴</li>
				<li class="tab-link" data-tab="tab-2">지도</li>
				<li class="tab-link" data-tab="tab-3">리뷰</li>
				<li class="tab-link" data-tab="tab-4">정보</li>
			</ul>
			<!-- 탭 메뉴 상단 끝 -->
			<!-- 탭 메뉴 내용 시작 -->
			<div id="tab-1" class="tab-content current"></div>
			<div id="tab-2" class="tab-content"></div>
			<div id="tab-3" class="tab-content"></div>
			<div id="tab-4" class="tab-content"></div>
			<!-- 탭 메뉴 내용 끝 -->
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})

});
</script>
</html>