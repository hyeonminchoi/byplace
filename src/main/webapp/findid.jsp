<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/favicon.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link href="./css/findid.css" rel="stylesheet">

</head>
<body>
	<div id="findidForm">
	<h1>아이디 찾기</h1>
		<hr>
		<form action="./findid" method="post">
		<table>
			<tr>
				<td>
				<label>이름</label>
				<input type="text" name="name" placeholder="이름을 입력하세요." required="required">
				<td>
			</tr>
			<tr>
				<td>
				<label>전화번호</label>
				<input type="text" name="phone" placeholder="전화번호를 입력하세요." required="required">
				</td>
			</tr>
		</table>
			<button type="submit">찾기</button>
			<button type="button" onclick="history.back()">취소</button>
		</form>
	</div>
   
   
   <!-- error 처리 -->
	<c:if test="${not empty error }">
		<h6><font color="red">해당하는 정보로 아이디를 찾지 못했습니다</font></h6>
	</c:if>
		
</body>
</html>