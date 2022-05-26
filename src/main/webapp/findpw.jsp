<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
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
function pw_search(){

	  var form=document.pwfindscreen;

	  if(form.user_id.value.length == 0){
			alert("아이디를 올바르게 입력해주세요");
			return false;
	  	}
	  if(form.user_name.value.length == 0){
			alert("이름을 올바르게 입력해주세요");
			return false;
		}

	   if (form.user_phone.value.length == 0) {
			alert("전화번호를 정확하게 입력해주세요");
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
	<div id="findpwForm">
	<hr>
	<form name="pwfindscreen" method="post">
		<div class="search-title">
			<h3>등록한 정보로 인증</h3>
		</div>
		<section class="form-search">
		<div class="find-id">
			<label>아이디</label> <input type="text" name=" user_id" class="btn-name" placeholder="아이디를 입력하세요."> <br>
		</div>
		<div class="find-name">
			<label>아이디</label> <input type="text" name=" user_name" class="btn-name" placeholder="이름을 입력하세요."> <br>
		</div>
		<div class="find-birthaday">
			<label>아이디</label> <input type="text" name=" user_birthday" class="btn-name" placeholder="생년월일을 입력하세요."> <br>
		</div>
		<div class="find-phone">
			<label>번호</label> <input type="text" onKeyup="addHypen(this);" name="user_phone" class="btn-phone" placeholder="휴대폰번호를 '-'없이 입력하세요.">
		</div><br>
		</section>
		<div class="btnSearch">
			<input type="button" name="enter" value="찾기" onClick="pw_search()">
			<input type="button" name="cancle" value="취소" onClick="history.back()">
		</div>
	</form>
	</div>
</body>
</html>