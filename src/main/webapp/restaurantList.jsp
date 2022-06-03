<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/favicon.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/restaurantlist.css">
<title>음식점 목록</title>
<style type="text/css">
#main{
	text-align: center;
}

</style>
</head>
<body>
<h1>맛집 리스트</h1>
<hr>
<div id="list">
<table>
<tr>
	<th id="col1" colspan="2">번호</th>
	<th id="col2" colspan="3">제목</th>
	<th id="col3" colspan="4">소개</th>
	<th id="col4" colspan="4">주소</th>
	<th id="col5" colspan="3">사진</th>
</tr>
<c:forEach items="${reslist }" var="r">
<tr>
	<td colspan="2">${r.restaurant_no }</td>
	<td id="tleft" colspan="3">
	<a href="./restaurantdetail?restaurant_no=${r.restaurant_no }">
	${r.restaurant_name }
	</a>
	</td>
	<td colspan="4">${r.restaurant_description }</td>
	<td colspan="4">${r.restaurant_roadAddress } / ${r.restaurant_detailAddress } / ${r.restaurant_extraAddress }</td>
	<td colspan="3"><img alt="음식점 사진" src="./restaurantImage/${r.restaurant_image }" style="width: 200px; height: 200px;"></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>