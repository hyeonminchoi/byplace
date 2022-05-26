<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음식점 목록</title>
<style type="text/css">
h1{
	text-align: center;
}
main{
	text-align: center;
}
</style>
</head>
<body>
<h1>맛집 리스트</h1>
<hr>
<div id="main">
<table>
<tr>
	<th id="col1">번호</th>
	<th id="col2">제목</th>
	<th id="col3">소개</th>
	<th id="col4">주소</th>
	<th id="col5">사진</th>
</tr>
<c:forEach items="${reslist }" var="r">
<tr>
	<td>${r.restaurant_no }</td>
	<td id="tleft">
	<a href="./restaurantdetail?restaurant_no=${r.restaurant_no }">
	${r.restaurant_name }
	</a>
	</td>
	<td>${r.restaurant_description }</td>
	<td>${r.restaurant_roadAddress } / ${r.restaurant_detailAddress } / ${r.restaurant_extraAddress }</td>
	<td><img alt="음식점 사진" src="./restaurantImage/${r.restaurant_image }" style="width: 200px; height: 200px;"></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>