<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/favicon.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/comment.css" rel="stylesheet">
<style type="text/css">
#main{
	margin: 0 auto;
	width: 800px;
	height: 100%;
}
table {
	width: 100%;
	min-height: 300px;
	border-collapse: collapse;
}
th{
	width: 100px;
	background-color: #c1c1c1;
}
tr{
	min-height:50px;
	border-bottom: 1px red solid;
}
</style>
</head>
<body>
	<%@include file="./menu.jsp"%>
	<div id="main">
		<h1>댓글 수정하기</h1>
		<div id="comment">
			<form action="./reportcup" method="post">
				<textarea name="comment">${dto.reportboardcomment_comment }</textarea>
				<input type="hidden" name="reportboardcomment_no" value="${dto.reportboardcomment_no }">
				<input type="hidden" name="reportboard_no" value="${dto.reportboard_no }">
				<button type="submit">댓글수정</button>
			</form>
		</div>
		<button onclick="location.href='./reportboard'">보드로 돌아가기</button>
		<button onclick="location.href='./reportdetail?reportboard_no=${dto.reportboard_no}'">글로 돌아가기</button>
	</div>
</body>
</html>



