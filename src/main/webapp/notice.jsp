<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/board.css" rel="stylesheet">

</head>
<body>
	<%@include file="./menu.jsp"%>
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
			<h1 style="text-align: left;">공지사항</h1>
			<table align="center" id="boardname">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>쓴사람</th>
					<th>쓴날짜</th>
				</tr>
				<c:forEach items="${list }" var="i">
					<tr>
						<td>${i.notice_no }</td>
						<td id="tleft"><a
							href="./noticeDetail?notice_no=${i.notice_no }"> <c:if
									test="${i.notice_filename ne null }">
									<img alt="file" src="" height="15px;">
								</c:if> ${i.notice_title  }
						</a></td>
						<td>${i.user_no }</td>
						<td>${i.notice_date }</td>
					</tr>
				</c:forEach>
			</table>

			<c:if
				test="${sessionScope.USER ne null && sessionScope.USER.user_type eq '관리자' }">
				<button onclick="location.href='./noticeWrite.jsp'">글쓰기</button>
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


			<button onclick="location.href='./notice?pageNo=1'">앞으로</button>
			<c:if test="${pageNo eq 1 }">
				<button disabled="disabled"
					onclick="location.href='./notice?pageNo=${pageNo - 1}'">
					left</button>
			</c:if>
			<c:if test="${pageNo ne 1 }">
				<button onclick="location.href='./notice?pageNo=${pageNo - 1}'">
					left</button>
			</c:if>

			<c:forEach begin="${startpage }" end="${endpage }" var="n">
				<button onclick="location.href='./notice?pageNo=${n }'">${n }</button>
			</c:forEach>

			<c:if test="${pageNo eq totalpage }">
				<button disabled="disabled"
					onclick="location.href='./notice?pageNo=${pageNo + 1}'">
					right</button>
			</c:if>
			<c:if test="${pageNo ne totalpage }">
				<button onclick="location.href='./notice?pageNo=${pageNo + 1}'">
					right</button>
			</c:if>

			<button onclick="location.href='./notice?pageNo=${totalpage}'">뒤로</button>

		</div>
	</div>
</body>
</html>