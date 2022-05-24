<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 초기화</title>
<style type="text/css">
#pwfindForm{
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
   var phone = form.phone;
   if(phone.value.length == 0){
      alert("전화번호를 입력하세요");
      phone.focus();
      return false;
   }
   
      return false;
   }
</script>
</head>
<body>
<form name="pwfindForm" method = "POST">
			<div id = "pwfindForm">
				<h1>비밀번호 찾기</h1>
				<hr>
				<div>
				<label>아이디</label><input type="text" name="id" placeholder = "아이디" required="required"><br>
				</div>
				<div>
				<label>전화번호</label><input type="text" name="phone" placeholder = "휴대폰번호를 '-'없이 입력" required="required"><br>
				</div>
				<div id ="findbtn">
					<input type="button" name="enter" value="찾기"  onClick="id_search()">
					<input type="button" name="cancle" value="취소" onClick="history.back()">
			 	</div>
			</div>
			<br>
	
 </form>

</body>
</html>