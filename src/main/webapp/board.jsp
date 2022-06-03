<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<%@ include file="/favicon.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

<link href="./css/menu.css" rel="stylesheet">
<link href="./css/board.css" rel="stylesheet">
</head>
<body>
	<%@ include file="./menu.jsp"%>
	<div id="main">
		<div id="side">
			<div id="mmenu">
				<ul>
					<li><a href="./notice">공지사항</a></li>
					<li><a href="./board">자유게시판</a></li>
					<li><a href="./reportboard">신고게시판</a></li>
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
				
					<button type="button" onclick="location.href='./boardWrite'"
						id="whitebtn">글쓰기</button>
				
			</c:if>

			<hr>

			<fmt:parseNumber integerOnly="true" var="totalpage"
				value="${totalcount / 10 }" />
			<c:if test="${(totalcount % 10) > 0 }">
				<c:set var="totalpage" value="${totalpage + 1}" />
			</c:if>

			<c:if test="${pageNo % 10 ne 0 }">
				<fmt:parseNumber integerOnly="true" var="startpage"
					value="${pageNo / 10 }" />
				<c:set var="startpage" value="${startpage * 10 + 1 }" />
			</c:if>
			<c:if test="${pageNo % 10 eq 0 }">
				<c:set var="startpage" value="${pageNo - 9 }" />
			</c:if>


			<c:set var="endpage" value="${startpage + 9 }" />
			<c:if test="${startpage + 10 gt totalpage }">
				<c:set var="endpage" value="${totalpage }" />
			</c:if>


			<button onclick="location.href='./board?pageNo=1'">앞으로</button>
			<c:if test="${pageNo eq 1 }">
				<button disabled="disabled"
					onclick="location.href='./board?pageNo=${pageNo - 1}'">
					left</button>
			</c:if>
			<c:if test="${pageNo ne 1 }">
				<button onclick="location.href='./board?pageNo=${pageNo - 1}'">
					left</button>
			</c:if>

			<c:forEach begin="${startpage }" end="${endpage }" var="n">
				<button onclick="location.href='./board?pageNo=${n }'">${n }</button>
			</c:forEach>

			<c:if test="${pageNo eq totalpage }">
				<button disabled="disabled"
					onclick="location.href='./board?pageNo=${pageNo + 1}'">
					right</button>
			</c:if>
			<c:if test="${pageNo ne totalpage }">
				<button onclick="location.href='./board?pageNo=${pageNo + 1}'">
					right</button>
			</c:if>

			<button onclick="location.href='./board?pageNo=${totalpage}'">뒤로</button>

		</div>
	</div>

</body>
</html>