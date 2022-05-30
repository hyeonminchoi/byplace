<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 게시판</title>
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
		<h1>신고자 게시판</h1>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>
				<th>읽음</th>
			</tr>
			<c:forEach items="${list }" var="i">
				<tr>
					<td>${i.reportboard_no }</td>
					<c:choose>
						<c:when test="${sessionScope.USER.user_type eq '관리자' or sessionScope.USER.user_no eq i.user_no }">
							<td id="tleft"><a href="./reportdetail?reportboard_no=${i.reportboard_no }">${i.reportboard_title  }
									<c:if test="${i.reportboard_commentcount gt 0 }">
										<small>${i.reportboard_commentcount }</small>
									</c:if>
							</a></td>
						</c:when>
						<c:otherwise>
							<td id="tleft">
								${i.reportboard_title  }
											<c:if test="${i.reportboard_commentcount gt 0 }">
												<small>${i.reportboard_commentcount }</small>
											</c:if>
							</td>
						</c:otherwise>
					</c:choose>
					<td>${i.reportboard_date }</td>
					<td>${i.reportboard_count }</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${sessionScope.USER ne null}">
			<button type="button" onclick="location.href='./reportboardWrite'">글쓰기</button>
		</c:if>
	</div>

</body>
</html>