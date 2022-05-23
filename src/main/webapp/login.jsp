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
	<div id="loginForm">
	<h1>Login</h1>
		<hr>
		<form action="login" method="post">
			아이디:<input type="text" name="id" placeholder="ID를 입력하세요." required="required"><br>
			비밀번호:<input type="password" name="pw" placeholder="암호를 입력하세요." required="required"><br>
			<input type="submit" value="로그인"><br>
		</form>
	</div>
</body>
</html>