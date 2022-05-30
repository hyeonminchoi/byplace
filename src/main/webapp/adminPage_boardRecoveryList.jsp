<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="./css/adminPage.css">
<style type="text/css">
	#boardList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#boardList tr, td, th{
		border: 1px solid black;
	}
	#boardList th, td{
		text-align: center;
	}
	table.pagination { border-collapse: collapse; margin: 20px auto; display: table; }
	table.pagination td { text-align: center; border: 1px solid #aaa; }
	table.pagination a { text-decoration: none; display: inline-block; padding: 5px 10px; color: #06C; }   
	table.pagination td.active { background-color: #06C; }
	table.pagination td.active a { color: white; }
	dialog{
		margin: auto;
		padding: 0;
		width: 1000px;
		height: 1000px;
	}
	dialog #btn{
		margin: 10px auto;
		padding: 5px;
		width: 80px;
		height: auto;
	}
	dialog img{
		margin: 10px auto;
		width: 300px;
		height: auto;
	}
	#comment{
		width: 100%;
		margin: 10px auto;
		height: 500px;
		border: 1px solid black;
	}
	#summernote{
		width: 100%;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var urlSearch = new URLSearchParams(location.search);
	urlSearch.set("pg", ${pg});
	newUrl = location.pathname + '?' +urlSearch
	history.pushState(null, null, newUrl);
});
function search(){
	var searchColumn = $("#searchColumn").val();
	var searchValue = $("#searchValue").val();
	var urlSearch = new URLSearchParams(location.search);
	urlSearch.set("pg", "1");
	urlSearch.set("searchColumn", searchColumn);
	urlSearch.set("searchValue", searchValue);
	location.href="./adminPage_boardRecoveryList?" + urlSearch;
}
function changed(){
	var sort = $("#board_sort").val();
	var searchColumn = new URLSearchParams(location.search).get("searchColumn");
	var searchValue = new URLSearchParams(location.search).get("searchValue");
	$.ajax({
		url: "./adminPage_boardRecoveryList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}, "searchColumn":searchColumn, "searchValue": searchValue},
		success: function(boardList){
			$("#board_body").empty();
			var temp = "";
			for(var i in boardList){
				var board = JSON.stringify(boardList[i]);
				temp += "<tr>";
				temp += "	<td>" + boardList[i].board_no + "</td>";
				temp += "	<td>" + boardList[i].board_title + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\'showComment(" + board + ")\'>본분보기</button>" + "</td>";
				temp += "	<td>" + boardList[i].board_commentcount + "</td>";
				temp += "	<td>" + boardList[i].board_date + "</td>";
				temp += "	<td>" + boardList[i].board_count + "</td>";
				temp += "	<td>" + boardList[i].user_id + "</td>";
				if(boardList[i].board_del == 1){
					temp += "	<td>" + "삭제" + "</td>";
				} else{
					temp += "	<td>" + "차단" + "</td>";
				}
				temp += "	<td>" + "<button type=\"button\" onclick=\"recoveryBoard(" + boardList[i].board_no + ")\">복구</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"dropBoard(" + boardList[i].board_no + ")\">제거</button>" + "</td>";
				temp += "</tr>";
			}
			$("#board_body").append(temp);
			$("#board_sort").val(sort).prop("selected",true);
			$("#searchValue").val(searchValue);
			if(searchColumn != null)
				$("#searchColumn").val(searchColumn).prop("selected",true);
			else
				$("#searchColumn option:eq(0)").prop("selected",true);
		},
		error: function(){
			alert("서버가 동작하지 않습니다.");
		}
	});
}
window.onload = function(){
	$("#board_sort").trigger("change");
}
</script>
</head>
<body>
	<!-- 관리자 권한 있는 사람만 접속 가능 -->
	<%-- <c:if test="${sessionScope.USER.user_type ne '관리자' }">
		<script>
			location.href="./index.jsp";
		</script>
	</c:if> --%>
	
	<%@ include file="./menu/adminPageSideBar.jsp"%>
	
	<section class="home-section">
		<div class="home-content">
			<h1>복구리스트</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="board_sort" id="board_sort" onchange="changed()">
						<option value="board_no asc"  <c:if test="${sort eq 'board_no asc'}">selected</c:if>>게시물 번호↑</option>
						<option value="board_no desc" <c:if test="${sort eq 'board_no desc'}">selected</c:if>>게시물 번호↓</option>
						<option value="board_title asc"  <c:if test="${sort eq 'board_title asc'}">selected</c:if>>제목↑</option>
						<option value="board_title desc" <c:if test="${sort eq 'board_title desc'}">selected</c:if>>제목↓</option>
						<option value="board_date asc"  <c:if test="${sort eq 'board_date asc'}">selected</c:if>>작성일↑</option>
						<option value="board_date desc" <c:if test="${sort eq 'board_date desc'}">selected</c:if>>작성일↓</option>
						<option value="board_count asc"  <c:if test="${sort eq 'board_count asc'}">selected</c:if>>조회수↑</option>
						<option value="board_count desc" <c:if test="${sort eq 'board_count desc'}">selected</c:if>>조회수↓</option>
						<option value="board_del asc" <c:if test="${sort eq 'board_del asc'}">selected</c:if>>상태↑</option>
						<option value="board_del desc" <c:if test="${sort eq 'board_del desc'}">selected</c:if>>상태↓</option>
					</select>
				</div>
			</div>
			<table id="boardList">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>내용</th>
						<th>댓글수</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>작성자</th>
						<th>상태</th>
						<th>복구</th>
						<th>제거</th>
					</tr>
				</thead>
				<tbody id="board_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
			<div class="search">
				<select id="searchColumn">
					<option value="board_title" ${searchColumn eq 'board_title' ? 'selected' : ''}>제목</option>
					<option value="board_comment" ${searchColumn eq 'board_comment' ? 'selected' : ''}>내용</option>
					<option value="user_id" ${searchColumn eq 'user_id' ? 'selected' : ''}>작성자</option>
				</select>
				<input type="text" id="searchValue">
				<button type="button" onclick="search()">검색</button>
			</div>
		</div>
	</section>

<dialog id="commentDialog">
	<div>
		<div id="comment"></div>
	</div>
	<button class="btn" type="button" onclick="hideComment()">닫기</button>
</dialog>

<script>
	function recoveryBoard(no){
		if(confirm("복구하겠습니까?")){
			location.href='./adminBoardRecovery?board_no=' + no;
		} else{
			location.href='./adminPage_boardList';
		}
	}
	
	function dropBoard(no){
		if(confirm("제거하겠습니까?")){
			location.href='./adminBoardDrop?board_no=' + no;
		} else{
			location.href='./adminPage_boardList';
		}
	}
	
	var commentDialog = document.getElementById("commentDialog");
	function showComment(board){
		$("#comment").html(board.board_comment);
		commentDialog.showModal();
	}
	function hideComment(){
		commentDialog.close();
	}
	
	let arrow = document.querySelectorAll(".arrow");
	for (var i = 0; i < arrow.length; i++) {
	  arrow[i].addEventListener("click", (e)=>{
	 	let arrowParent = e.target.parentElement.parentElement;
	 	arrowParent.classList.toggle("showMenu");
	  });
	}
</script>
</body>
</html>