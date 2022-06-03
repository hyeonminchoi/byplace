<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 상세보기</title>
<link href="./css/menu.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
td{
	width : calc(100% - 100px);
}
</style>
<c:if test="${noticedetail.user_id eq sessionScope.USER.user_id}">
<script type="text/javascript">
$(document).ready(function(){
	var notice_no = ${noticedetail.notice_no };
	$("#up").click(function(){
		if(confirm("해당 글을 수정하시겠습니까?")){
			location.replace("./noticeupdate?notice_no="+notice_no);
		}
	});
	
	$("#del").click(function(){
		if(confirm("해당 글을 삭제하시겠습니까?")){
			var notice_no = $("#notice_no").text();
			//alert(b_no2 + "번글을 삭제합니다.");
			location.replace("./noticedelete?notice_no="+notice_no);
		}
	});
});
</script>
</c:if>
</head>
<body>
	<%@include file="./menu.jsp"%>
	<div id="main">
		<h1>공지 상세 보기</h1>
		<table>
			<tr>
				<td colspan="2">
				${noticedetail.notice_title }
				<c:if test="${noticedetail.user_id eq sessionScope.USER.user_id}">
						<img id="up" alt="" src="./img/update.png" title="수정">
						<img id="del" alt="" src="./img/del.png" title="삭제">
				</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="3">${noticedetail.notice_date }</td>
			</tr>
			<tr>
				<td colspan="4">${noticedetail.notice_comment }</td>
			</tr>
		</table>
		<hr>
		<img alt="업로드된 파일" src="./upload/${noticedetail.notice_filename }">
		
		
	<button onclick="location.href='./notice'">게시판으로</button>
	</div>
	
</body>
</html>



