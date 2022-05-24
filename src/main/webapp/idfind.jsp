<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style type="text/css">
	#idfindForm{
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
   var name = form.name;
   if(name.value.length == 0){
      alert("이름을 입력하세요");
      name.focus();
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
<form name="idfindForm" method = "POST">
			<div id = "idfindForm">
				<h1>아이디 찾기</h1>
				<hr>
				<form action="find" method="post">
				<div>
				이름 :<input type="text" name="name" placeholder = "등록한 이름" required="required"><br>
				</div>
				<div>
				전화번호 :<input type="text" name="phone" placeholder = "휴대폰번호를 '-'없이 입력" required="required"><br>
				</div>
				<div id ="findbtn">
					<input type="button" name="enter" value="찾기"  onClick="id_search()">
					<input type="button" name="cancle" value="취소" onClick="history.back()">
			 	</div>
				</form>
			</div>
			<br>
	
 </form>

</body>
</html>