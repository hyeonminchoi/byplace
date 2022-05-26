<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style type="text/css">
	#findidForm{
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
   	if (form.user_bithday.value.length == 0) {
		alert("생년월일을 정확하게 입력해주세요");
		return false;
	 }
   
      return false;
   }
</script>
</head>
<body>
	<div id="findidForm">
	<h1>아이디 찾기</h1>
		<hr>
		<form action="./findid" method="post">
			<div>
			이름:<input type="text" name="name" placeholder="이름을 입력하세요." required="required"><br>
			</div>
			<div>
			이름:<input type="text" name="name" placeholder="이름을 입력하세요." required="required"><br>
			</div>
			<div>
			전화번호:<input type="text" name="phone" placeholder="전화번호를 입력하세요." required="required"><br>
			</div>
			<button type="submit">찾기</button>
		</form>
	</div>
   
   
   <!-- error에 대한 처리 -->
   <%if(request.getParameter("error") != null){ %>
   <h6><font color="red">해당하는 정보로 아이디를 찾지 못했습니다</font></h6>
   <%} %>
		
</body>
</html>