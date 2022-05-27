<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style type="text/css">
th {
	background-color: gray;
}

td {
	border-bottom-style: 1px solid red;
	text-align: center;
}
</style>
</head>
<body>
	<div id="main">
		<h1>BY Place</h1>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>
				<th>읽음</th>
			</tr>
			<c:forEach items="${list }" var="i">
				<tr>
					<td>${i.board_no }</td>
					<td id="tleft"><a href="./detail?board_no=${i.board_no }">${i.board_title  }
							<c:if test="${i.board_commentcount gt 0 }">
								<small>${i.board_commentcount }</small>
							</c:if>
					</a></td>
					<td>${i.board_date }</td>
					<td>${i.board_count }</td>
				</tr>
			</c:forEach>
		</table>
		
		<c:if test="${not empty sessionScope.USER }">
			<button type="button" onclick="location.href='./boardWrite'">글쓰기</button>
		</c:if>
	</div>

</body>
</html>