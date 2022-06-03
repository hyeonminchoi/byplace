<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ include file="/favicon.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style type="text/css">
	#noticeList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#noticeList tr, td, th{
		border: 1px solid black;
	}
	#noticeList th, td{
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="./css/adminPage.css">
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
	location.href="./adminPage_noticeList?" + urlSearch;
}
function changed(){
	var sort = $("#notice_sort").val();
	var searchColumn = new URLSearchParams(location.search).get("searchColumn");
	var searchValue = new URLSearchParams(location.search).get("searchValue");
	$.ajax({
		url: "./adminPage_noticeList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}, "searchColumn":searchColumn, "searchValue": searchValue},
		success: function(noticeList){
			$("#notice_body").empty();
			var temp = "";
			for(var i in noticeList){
				var notice = JSON.stringify(noticeList[i]);
				temp += "<tr>";
				temp += "	<td>" + noticeList[i].notice_no + "</td>";
				temp += "	<td>" + noticeList[i].notice_title + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\'showComment(" + notice + ")\'>본분보기</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"showNoticeImage(" + noticeList[i].notice_no + ",\'" + noticeList[i].notice_filename + "\')\">사진보기</button>" + "</td>";
				temp += "	<td>" + noticeList[i].notice_date + "</td>";
				if(noticeList[i].notice_del == 0){
					temp += "	<td>" + "게시" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\'showNoticeEditDialog(" + notice + ")\'>수정</button>" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\"deleteNotice(" + noticeList[i].notice_no + ")\">삭제</button>" + "</td>";
				} else if(noticeList[i].notice_del == 1){
					temp += "	<td>" + "삭제" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\'showNoticeEditDialog(" + notice + ")\'>수정</button>" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\"recoveryNotice(" + noticeList[i].notice_no + ")\">복구</button>" + "</td>";
				}
				temp += "</tr>";
			}
			$("#notice_body").append(temp);
			$("#notice_sort").val(sort).prop("selected",true);
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
	$("#notice_sort").trigger("change");
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
			<h1>공지사항리스트</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="notice_sort" id="notice_sort" onchange="changed()">
						<option value="notice_no asc"  <c:if test="${sort eq 'notice_no asc'}">selected</c:if>>게시물 번호↑</option>
						<option value="notice_no desc" <c:if test="${sort eq 'notice_no desc'}">selected</c:if>>게시물 번호↓</option>
						<option value="notice_title asc"  <c:if test="${sort eq 'notice_title asc'}">selected</c:if>>제목↑</option>
						<option value="notice_title desc" <c:if test="${sort eq 'notice_title desc'}">selected</c:if>>제목↓</option>
						<option value="notice_date asc"  <c:if test="${sort eq 'notice_date asc'}">selected</c:if>>작성일↑</option>
						<option value="notice_date desc" <c:if test="${sort eq 'notice_date desc'}">selected</c:if>>작성일↓</option>
						<option value="notice_del asc"  <c:if test="${sort eq 'notice_del asc'}">selected</c:if>>상태↑</option>
						<option value="notice_del desc" <c:if test="${sort eq 'notice_del desc'}">selected</c:if>>상태↓</option>
					</select>
				</div>
			</div>
			<table id="noticeList">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>내용</th>
						<th>사진</th>
						<th>작성일</th>
						<th>상태</th>
						<th>수정</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody id="notice_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
			<div class="search">
				<select id="searchColumn">
					<option value="notice_title" ${searchColumn eq 'notice_title' ? 'selected' : ''}>제목</option>
					<option value="notice_comment" ${searchColumn eq 'notice_comment' ? 'selected' : ''}>내용</option>
				</select>
				<input type="text" id="searchValue">
				<button type="button" onclick="search()">검색</button>
			</div>
		</div>
	</section>

<dialog id="noticeEditDialog">
	<div>
		<h1>게시물 수정</h1>
		<form action="./adminNoticeEdit" method="post">
 			<div>
 				<label>no</label>
 				<input type="number" name="notice_no" id="notice_no" readonly="readonly">
 			</div>
			<div>
				<label>제목</label>
				<input type="text" id="notice_title" name="notice_title"  required="required">
			</div>
			<div>
				<label>내용</label>
				<textarea id="notice_comment" name="notice_comment"></textarea>
			</div>
			
			<div>
				<button class="btn" type="submit">수정</button>
				<button class="btn" type="button" onclick="hideNoticeEditDialog()">취소</button>
			</div>
		</form>
	</div>
</dialog>

<dialog id="commentDialog">
	<div>
		<div id="comment"></div>
	</div>
	<button class="btn" type="button" onclick="hideComment()">닫기</button>
</dialog>

<dialog id="noticeImageDialog">
	<div>
		<img id="image" alt="로딩 실패">
	</div>
	<button type="button" onclick="showNoticeImageEdit()">수정</button>
	<button type="button" onclick="hideNoticeImage()">닫기</button>
</dialog>

<dialog id="noticeImageEditDialog">
	<form action="./adminNoticeImageEdit" method="post" enctype="multipart/form-data">
		<input type="hidden" name="notice_no" id="no">
		<div>
			<input type="file" name="notice_image">
		</div>
		<button type="submit">변경</button>
		<button type="button" onclick="hideNoticeImageEdit()">닫기</button>
	</form>
</dialog>

<script>
	function deleteNotice(no){
		if(confirm("삭제하겠습니까?")){
			location.href='./adminNoticeDelete?notice_no=' + no;
		} else{
			location.href='./adminPage_noticeList';
		}
	}
	
	function recoveryNotice(no){
		if(confirm("복구하겠습니까?")){
			location.href='./adminNoticeRecovery?notice_no=' + no;
		} else{
			location.href='./adminPage_noticeList';
		}
	}
	
	var noticeImageDialog = document.getElementById("noticeImageDialog");
	function showNoticeImage(no, image){
		$("#no").val(no);
		$("#image").attr("src", "./noticeImage/" + image);
		noticeImageDialog.showModal();
	}
	
	function hideNoticeImage(){
		noticeImageDialog.close();		
	}
	
	var noticeImageEditDialog = document.getElementById("noticeImageEditDialog");
	function showNoticeImageEdit(){
		noticeImageEditDialog.showModal();
	}
	
	function hideNoticeImageEdit(){
		noticeImageEditDialog.close();		
	}
	
	var commentDialog = document.getElementById("commentDialog");
	function showComment(notice){
		$("#comment").html(notice.notice_comment);
		commentDialog.showModal();
	}
	function hideComment(){
		commentDialog.close();
	}
	
	var noticeEditDialog = document.getElementById("noticeEditDialog");
	function showNoticeEditDialog(notice){
		$("#notice_no").val(notice.notice_no);
		$("#notice_title").val(notice.notice_title);
		$('#notice_comment').val(notice.notice_comment);
		noticeEditDialog.showModal();
	}
	
	function hideNoticeEditDialog(){
		noticeEditDialog.close();
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