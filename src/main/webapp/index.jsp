<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#main{
	margin: 0 auto;
	width: 800px;
	height: 100%;
}
#loginform{
	margin:0 auto;
	margin-top:150px;
	height: 450px;
	width: 300px;
	background-color: gray;
	padding: 10px;
}
#loginform input, button{
	margin: 0;
	padding: 0;
	background-color: white;
	border: 0;
	height: 40px;
	width: 100%;
}
#loginform input{
	margin-bottom: 10px;
}
#loginform input:hover, button:hover{
	background-color: green;
}
img{
	width: 50px;
	height: auto;
}
</style>
</head>
<body>
	<%@include file="./menu.jsp"%>
	
</body>
</html>


=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
<link href="./css/tabmenu.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js">
</script>
</head>
<body>
	<div id="main">
		<div class="container">
<!-- 탭 메뉴 상단 시작 -->
	<ul class="tabs">
		<li class="tab-link current" data-tab="tab-1">전체</li>
		<li class="tab-link" data-tab="tab-2">한식</li>
		<li class="tab-link" data-tab="tab-3">양식</li>
		<li class="tab-link" data-tab="tab-4">중식</li>
		<li class="tab-link" data-tab="tab-5">일식</li>
	</ul>
<!-- 탭 메뉴 상단 끝 -->
<!-- 탭 메뉴 내용 시작 -->
	<div id="tab-1" class="tab-content current">
    <h1>전체내용</h1>

	</div>
	<div id="tab-2" class="tab-content">
  	<h1>한식</h1>
	</div>
	<div id="tab-3" class="tab-content">
  	<h1>양식</h1>
	</div>
	<div id="tab-3" class="tab-content">
  	<h1>중식</h1>
	</div>
	<div id="tab-3" class="tab-content">
  	<h1>일식</h1>
	</div>
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
>>>>>>> refs/remotes/origin/main
