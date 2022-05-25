<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보보기</title>
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
</style>
</head>
<body>
	<%@include file="./menu.jsp"%>
	<div id="main">
		아이디 : ${user.id }<br>
		닉네임 : ${user.nickname }<br>
		이름 : ${user.name }<br>
		주소 : ${user.roadAddress}<br>
		이메일 : ${user.email }<br>
		전화번호 : ${user.phone }<br>
		가입일 : ${user.joined }<br>
	</div>
</body>
</html>



