<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<!DOCTYPE html>
<%@ include file="/favicon.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style type="text/css">
	#reportboardList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#reportboardList tr, td, th{
		border: 1px solid black;
	}
	#reportboardList th, td{
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
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<link rel="stylesheet" href="./css/adminPage.css">
<script type="text/javascript">
$(document).ready(function(){
	$('#summernote').summernote({
		height: 300
	});
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
	location.href="./adminPage_reportboardList?" + urlSearch;
}
function changed(){
	var sort = $("#reportboard_sort").val();
	var searchColumn = new URLSearchParams(location.search).get("searchColumn");
	var searchValue = new URLSearchParams(location.search).get("searchValue");
	$.ajax({
		url: "./adminPage_reportboardList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}, "searchColumn":searchColumn, "searchValue": searchValue},
		success: function(reportboardList){
			$("#reportboard_body").empty();
			var temp = "";
			for(var i in reportboardList){
				var reportboard = JSON.stringify(reportboardList[i]);
				temp += "<tr>";
				temp += "	<td>" + reportboardList[i].reportboard_no + "</td>";
				temp += "	<td><a href='./adminPage_reportboardcommentList?reportboard_no=" + reportboardList[i].reportboard_no + "&prevPg=" + ${pg} + "'>" + reportboardList[i].reportboard_title + "</a></td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\'showComment(" + reportboard + ")\'>본분보기</button>" + "</td>";
				temp += "	<td>" + reportboardList[i].reportboard_commentcount + "</td>";
				temp += "	<td>" + reportboardList[i].reportboard_date + "</td>";
				temp += "	<td>" + reportboardList[i].reportboard_count + "</td>";
				temp += "	<td>" + reportboardList[i].user_id + "</td>";
				if(reportboardList[i].reportboard_del == 0){
					temp += "	<td>" + "게시" + "</td>";
				} else if(reportboardList[i].reportboard_del == 1){
					temp += "	<td>" + "삭제" + "</td>";
				} else{
					temp += "	<td>" + "차단" + "</td>";
				}
				temp += "	<td>" + "<button type=\"button\" onclick=\'showReportboardEditDialog(" + reportboard + ")\'>수정</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"blockReportboard(" + reportboardList[i].reportboard_no + ")\">차단</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"deleteReportboard(" + reportboardList[i].reportboard_no + ")\">삭제</button>" + "</td>";
				temp += "</tr>";
			}
			$("#reportboard_body").append(temp);
			$("#reportboard_sort").val(sort).prop("selected",true);
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
	$("#reportboard_sort").trigger("change");
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
			<h1>신고게시판리스트</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="reportboard_sort" id="reportboard_sort" onchange="changed()">
						<option value="reportboard_no asc"  <c:if test="${sort eq 'reportboard_no asc'}">selected</c:if>>게시물 번호↑</option>
						<option value="reportboard_no desc" <c:if test="${sort eq 'reportboard_no desc'}">selected</c:if>>게시물 번호↓</option>
						<option value="reportboard_title asc"  <c:if test="${sort eq 'reportboard_title asc'}">selected</c:if>>제목↑</option>
						<option value="reportboard_title desc" <c:if test="${sort eq 'reportboard_title desc'}">selected</c:if>>제목↓</option>
						<option value="reportboard_comment asc"  <c:if test="${sort eq 'reportboard_comment asc'}">selected</c:if>>내용↑</option>
						<option value="reportboard_comment desc" <c:if test="${sort eq 'reportboard_comment desc'}">selected</c:if>>내용↓</option>
						<option value="reportboard_date asc"  <c:if test="${sort eq 'reportboard_date asc'}">selected</c:if>>작성일↑</option>
						<option value="reportboard_date desc" <c:if test="${sort eq 'reportboard_date desc'}">selected</c:if>>작성일↓</option>
						<option value="reportboard_del asc"  <c:if test="${sort eq 'reportboard_del asc'}">selected</c:if>>상태↑</option>
						<option value="reportboard_del desc" <c:if test="${sort eq 'reportboard_del desc'}">selected</c:if>>상태↓</option>
					</select>
				</div>
			</div>
			<table id="reportboardList">
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
						<th>수정</th>
						<th>차단</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody id="reportboard_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
			<div class="search">
				<select id="searchColumn">
					<option value="reportboard_title" ${searchColumn eq 'reportboard_title' ? 'selected' : ''}>제목</option>
					<option value="reportboard_comment" ${searchColumn eq 'reportboard_comment' ? 'selected' : ''}>내용</option>
					<option value="user_id" ${searchColumn eq 'user_id' ? 'selected' : ''}>작성자</option>
				</select>
				<input type="text" id="searchValue">
				<button type="button" onclick="search()">검색</button>
			</div>
		</div>
	</section>

<dialog id="reportboardEditDialog">
	<div>
		<h1>게시물 수정</h1>
		<form action="./adminReportboardEdit" method="post">
 			<div>
 				<label>no</label>
 				<input type="number" name="reportboard_no" id="reportboard_no" readonly="readonly">
 			</div>
			<div>
				<label>제목</label>
				<input type="text" id="reportboard_title" name="reportboard_title"  required="required">
			</div>
			<div>
				<label>내용</label>
				<textarea id="summernote" name="reportboard_comment"></textarea>
			</div>
			
			<div>
				<button class="btn" type="submit">수정</button>
				<button class="btn" type="button" onclick="hideReportboardEditDialog()">취소</button>
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
<script>
	function deleteReportboard(no){
		if(confirm("삭제하겠습니까?")){
			location.href='./adminReportboardDelete?reportboard_no=' + no;
		} else{
			location.href='./adminPage_reportboardList';
		}
	}
	
	function blockReportboard(no){
		if(confirm("차단하겠습니까?")){
			location.href='./adminReportboardBlock?reportboard_no=' + no;
		} else{
			location.href='./adminPage_reportboardList';
		}
	}
	
	var commentDialog = document.getElementById("commentDialog");
	function showComment(reportboard){
		$("#comment").html(reportboard.reportboard_comment);
		commentDialog.showModal();
	}
	function hideComment(){
		commentDialog.close();
	}
	
	var reportboardEditDialog = document.getElementById("reportboardEditDialog");
	function showReportboardEditDialog(reportboard){
		$("#reportboard_no").val(reportboard.reportboard_no);
		$("#reportboard_title").val(reportboard.reportboard_title);
		$('#summernote').summernote('code', reportboard.reportboard_comment);
		reportboardEditDialog.showModal();
	}
	
	function hideReportboardEditDialog(){
		reportboardEditDialog.close();
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