<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<script type="text/javascript">
function reportcup(num){
	if(confirm("해당 글을 수정하시겠습니까?")){		
		//alert(num + "번 글을 수정합니다.");
		location.href="./reportcup?reportboard_no=${reportdetail.reportboard_no }&reportboardcomment_no=" + num;
	}
}
function reportcdel(num){
	if(confirm("해당 글을 삭제하시겠습니까?")){		
		//alert(num + "번 글을 삭제합니다.");
		location.href="reportcdel?reportboard_no=${reportdetail.reportboard_no }&reportboardcomment_no="+num;
	}
}
</script>
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
td{
	width : calc(100% - 100px);
}
</style>
<c:if test="${reportdetail.user_id eq sessionScope.USER.user_id}">
<script type="text/javascript">
$(document).ready(function(){
	var reportboard_no = ${reportdetail.reportboard_no };
	$("#up").click(function(){
		if(confirm("해당 글을 수정하시겠습니까?")){
			location.replace("./reportupdate?reportboard_no="+reportboard_no);
		}
	});
	
	$("#del").click(function(){
		if(confirm("해당 글을 삭제하시겠습니까?")){
			var reportboarad_no = $("#reportboard_no").text();
			//alert(b_no2 + "번글을 삭제합니다.");
			location.replace("./reportdelete?reportboard_no="+reportboard_no);
		}
	});
});
</script>
</c:if>
</head>
<body>
	<div id="main">
		<h1>글 상세 보기</h1>
		<table>
			<tr>
				<td colspan="2">
				${reportdetail.reportboard_title }
				<c:if test="${reportdetail.user_id eq sessionScope.USER.user_id}">
						<img id="up" alt="수정" src="./img/update.png" title="수정">
						<img id="del" alt="삭제" src="./img/del.png" title="삭제">
				</c:if>
				</td>
			</tr>
			<tr>
				<td>${reportdetail.user_name } / ${reportdetail.user_id }</td>
				<td><img alt="조회수" src="./img/view.png" title="조회수" width="25px;"> ${reportdetail.reportboard_count }</td>
			</tr>
			<tr>
				<td colspan="3">${reportdetail.reportboard_date }</td>
			</tr>
			<tr>
				<td colspan="4">${reportdetail.reportboard_comment }</td>
			</tr>
		</table>
		<br>
	<c:if test="${sessionScope.USER ne null }">
		<div id="comment">
			<form action="./reportcommentWrite" method="post">
			<textarea name="comment"></textarea>
			<input type="hidden" name="reportboard_no" value="${reportdetail.reportboard_no }">
			<button type="submit">댓글쓰기</button>
			</form>
		</div>		
	</c:if>
		
		
		
		<c:forEach items="${reportcommList }" var="c">
		<div id="comment_row">
			<div id="comment_head">
				<div id="comment_id">
					${c.reportboardcomment_no } / ${c.user_no } / ${c.user_id } / ${c.user_name }
					<c:if test="${sessionScope.USER.user_id eq c.user_id }">
						<img alt="수정" src="./img/update.png" title="수정" onclick="reportcup(${c.reportboardcomment_no })">
						<img alt="삭제" src="./img/del.png" title="삭제" onclick="reportcdel(${c.reportboardcomment_no })">
					</c:if>
				</div>
				<div id="comment_date">${c.reportboardcomment_date }</div>	
			</div>
			<div id="comment_body">${c.reportboardcomment_comment }</div>
		</div>
		</c:forEach>
		
		
		
		
		
		<br>
	<button onclick="location.href='./reportboard'">게시판으로</button>
	</div>
	
</body>
</html>
