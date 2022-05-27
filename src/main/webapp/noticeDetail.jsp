<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 상세보기</title>
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#main{
	margin: 0 auto;
	width: 800px;
	height: 100%;
}
table {
	width: 100%;
	min-height: 300px;
	border-collapse: collapse;
}
th{
	width: 100px;
	background-color: #c1c1c1;
}
tr{
	min-height:50px;
	border-bottom: 1px red solid;
}
td{
	width : calc(100% - 100px);
}
</style>
</head>
<body>
	<%@include file="./menu.jsp"%>
	<div id="main">
		<h1>공지 상세 보기</h1>
		notice_no : ${dto.notice_no }<br>
		notice_title : ${dto.notice_title }<br>
		notice_comment : ${dto.notice_comment }<br>
		notice_date : ${dto.notice_date }<br>
		notice_orifilename : ${dto.notice_orifilename }<br>
		notice_filename : ${dto.notice_filename }<br>
		user_no : ${dto.user_no }<br>
		<hr>
		<img alt="업로드된 파일" src="./upload/${dto.notice_filename }">
		
		
	<button onclick="location.href='./notice'">게시판으로</button>
	</div>
	
</body>
</html>



