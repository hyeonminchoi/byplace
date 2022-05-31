<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
<link rel="apple-touch-icon" sizes="57x57" href="/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
<link rel="manifest" href="/img/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<meta charset="UTF-8">
<title>홈페이지</title>
<link href="./css/tabmenu.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#main{
	margin: 0 auto;
	width: 100%;
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
img{
	width: 50px;
	height: auto;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js">
</script>
</head>
<body>
	<%@include file="./menu.jsp" %>
<!-- 탭 메뉴 상단 시작 -->
	<%@include file="./tabmenu.jsp" %>
	
	<button type="button" onclick="showDialog()">개인정보</button>

	<c:if test="${not empty sessionScope.USER }">
		<dialog id="dialog">
			<form action="./userInfo" method="post">
				<label>비밀번호 입력</label>
				<input type="hidden" name="user_no" value=${sessionScope.USER.user_no }>
				<input type="password" name="password">
				<button type="submit">인증</button>
				<button type="button" onclick="hideDialog()">닫기</button>
			</form>
		</dialog>
	</c:if>
<script type="text/javascript">
	var dialog = document.getElementById("dialog");
	function showDialog(){
		dialog.showModal();
	}
	function hideDialog(){
		dialog.close();
	}
</script>
</body>
</html>
