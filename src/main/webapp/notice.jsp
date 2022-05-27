<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지</title>
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#main{
	margin: 0 auto;
	width: 800px;
	height: 100%;
}
table {
	width: 100%;
	min-height: 200px;
	border-collapse: collapse;
}
th{
	background-color: gray;
}
td{
	border-bottom: 1px solid red;
	text-align: center;
}
tr:hover{
	background-color: #c1c1c1;
}
#col1{
	width: 10%;
}
#col2{
	width: 20%;
}
#col3{
	width: 40%;
}
#tleft{
	text-align: left;
}
a:visited, a:link {
	color: black;
	text-decoration: none;
}
</style>
</head>
<body>
	<%@include file="./menu.jsp"%>
	<div id="main">
		<h1>공지사항</h1>
		<table>
			<tr>
				<th id="col1">번호</th>
				<th id="col3">제목</th>
				<th id="col1">쓴사람</th>
				<th id="col2">쓴날짜</th>
			</tr><c:forEach items="${list }" var="i">
			<tr>
				<td>${i.notice_no }</td>
				<td id="tleft">
				<a href="./noticeDetail?notice_no=${i.notice_no }">
				<c:if test="${i.notice_filename ne null }">
					<img alt="file" src="" height="15px;">
				</c:if>
				${i.notice_title  }</a></td>
				<td>${i.user_no }</td>
				<td>${i.notice_date }</td>
			</tr></c:forEach>
		</table>
		
		<c:if test="${sessionScope.USER ne null && sessionScope.USER.user_type eq '관리자' }">
			<button onclick="location.href='./noticeWrite.jsp'">글쓰기</button>
		</c:if>
		
	</div>
</body>
</html>