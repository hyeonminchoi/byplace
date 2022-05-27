<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style type="text/css">
#findpwForm{
		margin:0 auto;
		width: 500px;
		height: 300px;
		box-sizing: border-box;
		text-align: center;	
	}
</style>
</head>
<body>
	<div id="findpwForm">
	<h1>비밀번호 초기화</h1>
	<hr>
		<form action="./findpw" method="post">
			<h3>등록한 정보로 인증</h3>
		
			<div>
			<label>아이디</label> <input type="text" name="id" placeholder="아이디를 입력하세요." required="required"> <br>
			</div>
			<div>
			<label>이름</label> <input type="text" name="name" placeholder="이름을 입력하세요." required="required"> <br>
			</div>
			<div>
			<label>전화번호</label> <input type="text" name="phone" placeholder="전화번호를 입력하세요." required="required">
			</div>
			<button type="submit" >초기화</button>
			<button type="submit" onClick="history.back()">취소</button>
		</form>
	</div>

	<!-- 에러처리 -->
	<c:if test="${not empty error }">
		<h6><font color="red">에러</font></h6>
   	</c:if>
	
</body>
</html>