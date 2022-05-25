<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript">

function check() {
   var form = document.joinform;
   var id = form.id;
   if(id.value.length == 0){
      alert("아이디를 입력하세요");
      id.focus();
      return false;
   }
   var passwd = form.passwd;
   if(passwd.value.length == 0){
      alert("비밀번호를 입력하세요");
      passwd.focus();
      return false;
   }
   
      return false;
   }
</script>

</head>
<body>
	<c:if test="${not empty error }">
		<script>
			alert("${error}");
		</script>
	</c:if>
	<div id="loginForm">
	<h1>Login</h1>
		<hr>
		<form action="login" method="post">
			<div>
			아이디:<input type="text" name="id" placeholder="ID를 입력하세요." required="required"><br>
			</div>
			<div>
			비밀번호:<input type="password" name="pw" placeholder="암호를 입력하세요." required="required"><br>
			</div>
			<input type="submit" value="로그인"><br>
		</form>
		<a href="./findid.jsp">아이디 찾기</a>
		<a href="./findpw.jsp">비밀번호 초기화</a>
		<a href="./join.jsp">회원가입</a>
	</div>
</body>
</html>