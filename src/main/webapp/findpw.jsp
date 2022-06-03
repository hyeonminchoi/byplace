<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/favicon.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link href="./css/findpw.css" rel="stylesheet">
</head>
<body>
	<div id="findpwForm">
	<h1>비밀번호 초기화</h1>
	<hr>
		<form action="./findpw" method="post">
			<h3>등록한 정보로 인증</h3>
		<table>
			<tr>
			<td>
			<label>아이디</label>
			<input type="text" name="id" placeholder="아이디를 입력하세요." required="required">
			</td>
			</tr>
			<tr>
			<td>
			<label>이름</label>
			<input type="text" name="name" placeholder="이름을 입력하세요." required="required">
			</td>
			</tr>
			<tr>
			<td>
			<label>전화번호</label>
			<input type="text" name="phone" placeholder="전화번호를 입력하세요." required="required">
			</td>
			</tr>
		</table>
			<button type="submit" id="findpb">초기화</button>
			<button type="submit" onClick="history.back()" id="findpb">취소</button>
		</form>
	</div>

	<!-- 에러처리 -->
	<c:if test="${not empty error }">
		<h6><font color="red">에러</font></h6>
   	</c:if>
	
</body>
</html>