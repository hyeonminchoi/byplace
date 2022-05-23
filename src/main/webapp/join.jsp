<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#joinbox{
	width: 800px;
	height: 500px;
	margin: 0 auto;
	padding: 0;
	box-sizing: border-box;
	text-align: center;
}
</style>
<title>Join Us</title>
</head>
<body>
		<div id="joinbox">
		<h1>회원가입</h1>
		<hr>
			<h2>개인정보 입력</h2>
			<form name="joinform" action="./join" method="post" onsubmit="return check()">
			<input type="text" id="id" name="id" placeholder="아이디를 입력하세요" class="form-control" onchange= "idcheck()">
			<button type="submit" id="CheckId" class="btn btn-success">아이디 중복확인</button>
			<div id="checkId">아이디를 확인중입니다.</div>
			<input type="password" name="passwd" placeholder="비밀번호를 입력하세요" class="form-control"><br>
			<input type="text" name="name" placeholder="이름을 입력하세요" class="form-control"><br>
			<input type="number" name="age" placeholder="나이를 입력하세요" class="form-control"><br>
			<input type="text" name="addr" placeholder="주소를 입력하세요" class="form-control"><br>
			<input type="email" name="email" placeholder="이메일을 입력하세요" class="form-control"><br>
			<input type="tel" name="tel" placeholder="전화버호를 입력하세요" class="form-control"><br>
			<button type="submit" id="joinBtn" class="btn btn-success" >Join</button>
			</form>
	</div>

</body>
</html>