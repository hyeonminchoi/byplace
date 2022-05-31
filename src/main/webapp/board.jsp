<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

<link href="./css/menu.css" rel="stylesheet">
<link href="./css/board.css" rel="stylesheet">
</head>
<body>
	<%@ include file="./menu.jsp" %>
	<div id="main">
		<div id="side">
			<div id="mmenu">
				<ul>
					<li><a href="./notice">공지사항</a></li>
					<li><a href="#">자유게시판</a></li>
					<li><a href="#">신고게시판</a></li>
				</ul>			
			</div>
		</div>
		<div id="board">
			<h1 style="text-align: left;">자유게시판</h1>
			<table align="center" id="boardname">
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
				<div style=" text-align: center;">
				<button type="button" onclick="location.href='./boardWrite'" id="whitebtn">글쓰기</button>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>