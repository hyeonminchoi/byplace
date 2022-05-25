<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style type="text/css">
#idfindForm {
	margin: 0 auto;
	width: 500px;
	height: 300px;
	box-sizing: border-box;
	text-align: center;
}
</style>
<script type="text/javascript">
	function id_search() {
		var frm = document.idfindscreen;

		if (name.value.length < 1) {
			alert("이름을 입력해주세요");
			return;
		}

		if (phone.value.length != 13) {
			alert("핸드폰번호를 정확하게 입력해주세요");
			return;
		}

		form.method = "post";
		form.action = "findIdResult.jsp"; //넘어간화면
		form.submit();
	}
</script>
</head>
<body>
	<form name="idfindForm" method="POST">
		<div id="idfindForm">
			<h1>아이디 찾기</h1>
			<hr>
			<h3>휴대폰 본인확인</h3>
			<label>이름</label> <input type="text" name="name" placeholder="등록한 이름">
			<div>
				<label>번호</label>
				<input type="text" name="phone" onKeyup="addHypen(this);" placeholder="휴대폰번호를 '-'없이 입력">
			</div>
			<br>
			<div id="btnSearch">
				<input type="button" name="enter" value="찾기" onClick="id_search()">
				<input type="button" name="cancle" value="취소"
					onClick="history.back()">
			</div>
		</div>
	</form>
</body>
</html>