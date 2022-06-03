<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/favicon.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<script type="text/javascript">
function cup(num){
	if(confirm("해당 글을 수정하시겠습니까?")){		
		//alert(num + "번 글을 수정합니다.");
		location.href="./cup?board_no=${detail.board_no }&boardcomment_no=" + num;
	}
}
function cdel(num){
	if(confirm("해당 글을 삭제하시겠습니까?")){		
		//alert(num + "번 글을 삭제합니다.");
		location.href="cdel?board_no=${detail.board_no }&boardcomment_no="+num;
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
<c:if test="${detail.user_id eq sessionScope.USER.user_id}">
<script type="text/javascript">
$(document).ready(function(){
	var board_no = ${detail.board_no };
	$("#up").click(function(){
		if(confirm("해당 글을 수정하시겠습니까?")){
			location.replace("./update?board_no="+board_no);
		}
	});
	
	$("#del").click(function(){
		if(confirm("해당 글을 삭제하시겠습니까?")){
			var boarad_no = $("#board_no").text();
			//alert(b_no2 + "번글을 삭제합니다.");
			location.replace("./delete?board_no="+board_no);
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
				${detail.board_title }
				<c:if test="${detail.user_id eq sessionScope.USER.user_id}">
						<img id="up" alt="" src="./img/update.png" title="수정">
						<img id="del" alt="" src="./img/del.png" title="삭제">
				</c:if>
				</td>
			</tr>
			<tr>
				<td>${detail.user_name } / ${detail.user_id }</td>
				<td><img alt="조회수" src="./img/view.png" title="조회수" width="25px;"> ${detail.board_count }</td>
			</tr>
			<tr>
				<td colspan="3">${detail.board_date }</td>
			</tr>
			<tr>
				<td colspan="4">${detail.board_comment }</td>
			</tr>
		</table>
		<br>
	<c:if test="${sessionScope.USER ne null }">
		<div id="comment">
			<form action="./commentWrite" method="post">
			<textarea name="comment"></textarea>
			<input type="hidden" name="board_no" value="${detail.board_no }">
			<button type="submit">댓글쓰기</button>
			</form>
		</div>		
	</c:if>
		
		
		
		<c:forEach items="${commList }" var="c">
		<div id="comment_row">
			<div id="comment_head">
				<div id="comment_id">
					${c.boardcomment_no } / ${c.user_no } / ${c.user_id } / ${c.user_name }
					<c:if test="${sessionScope.USER.user_id eq c.user_id }">
						<img alt="수정" src="./img/update.png" title="수정" onclick="cup(${c.boardcomment_no })">
						<img alt="삭제" src="./img/del.png" title="삭제" onclick="cdel(${c.boardcomment_no })">
					</c:if>
				</div>
				<div id="comment_date">${c.boardcomment_date }</div>	
			</div>
			<div id="comment_body">${c.boardcomment_comment }</div>
		</div>
		</c:forEach>
		
		
		
		
		
		<br>
	<button onclick="location.href='./board'">게시판으로</button>
	</div>
	
</body>
</html>
