<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
function pwCheck(){
	var password = document.getElementById("password").value;
	var password1 = document.getElementById("password1").value;
	if(password != password1){
		$("#btn").attr("disabled", true);
		alert("비밀번호가 다릅니다.");
	}else{
		$("#btn").attr("disabled", false);
	}
}
</script>
</head>
<body>
	<c:if test="${empty user_no }">
		<script>
			location.href="./index.jsp";
		</script>
	</c:if>
	<form action="./findpwResult?user_no=${user_no}" method="post">
		<div>
			변경 비밀번호 :<input type="password" id="password" name="password" placeholder="이름을 입력하세요." required="required"><br>
		</div>
		<div>
			변경 비밀번호 확인:<input type="password" id="password1" placeholder="전화번호를 입력하세요." onchange="pwCheck()" required="required"><br>
		</div>
		<button id="btn" type="submit">초기화</button>
		<button type="button" onclick="location.href='./login.jsp'">취소</button>
	</form>
</body>
</html>