<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<style type="text/css">
	#loginForm{
		margin:0 auto;
		width: 500px;
		height: 300px;
		box-sizing: border-box;
		text-align: center;	
	}
</style>
</head>
<body>
	<div id="loginForm">
	<h1>Login</h1>
		<hr>
		<form action="login" method="post">
			아이디:<input type="text" name="id" placeholder="ID를 입력하세요."><br>
			비밀번호:<input type="password" name="pw" placeholder="암호를 입력하세요."><br>
			<input type="submit" value="로그인"><br>
		</form>
	</div>
</body>
</html>