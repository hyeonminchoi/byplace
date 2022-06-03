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
<link rel="stylesheet" href="./css/adminPage.css">
<style type="text/css">
	#reportboardcommentList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#reportboardcommentList tr, td, th{
		border: 1px solid black;
	}
	#reportboardcommentList td{
		text-align: center;
	}  
	table.pagination { border-collapse: collapse; margin: 20px auto; }
	table.pagination td { text-align: center; border: 1px solid #aaa; }
	table.pagination a { text-decoration: none; display: inline-block; padding: 5px 10px; color: #06C; }   
	table.pagination td.active { background-color: #06C; }
	table.pagination td.active a { color: white; }
	dialog{
		margin: auto;
		padding: 0;
		width: 500px;
		height: 500px;
	}
	dialog button{
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
	textarea{
		width: 100%;
		height: 300px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
	location.href="./adminPage_reportboardcommentList?" + urlSearch;
}
function changed(){
	var sort = $("#reportboardcomment_sort").val();
	var searchColumn = new URLSearchParams(location.search).get("searchColumn");
	var searchValue = new URLSearchParams(location.search).get("searchValue");
	$.ajax({
		url: "./adminPage_reportboardcommentList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}, "searchColumn":searchColumn, "searchValue": searchValue, "reportboard_no":${reportboard_no}},
		success: function(reportboardcommentList){
			$("#reportboardcomment_body").empty();
			var temp = "";
			for(var i in reportboardcommentList){
				var reportboardcomment = JSON.stringify(reportboardcommentList[i]);
				temp += "<tr>";
				temp += "	<td>" + reportboardcommentList[i].reportboardcomment_no + "</td>";
				temp += "	<td>" + reportboardcommentList[i].user_id + "</td>";
				temp += "	<td>" + reportboardcommentList[i].reportboardcomment_comment + "</td>";
				temp += "	<td>" + reportboardcommentList[i].reportboardcomment_date + "</td>";
				if(reportboardcommentList[i].reportboardcomment_del == 0){
					temp += "	<td>" + "게시" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\"deleteReportboardcomment(" + reportboardcommentList[i].reportboardcomment_no + ")\">삭제</button>" + "</td>";
				} else if(reportboardcommentList[i].reportboardcomment_del == 1){
					temp += "	<td>" + "삭제" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\"recoveryReportboardcomment(" + reportboardcommentList[i].reportboardcomment_no + ")\">복구</button>" + "</td>";
				}
				temp += "</tr>";
			}
			$("#reportboardcomment_body").append(temp);
			$("#reportboardcomment_sort").val(sort).prop("selected",true);
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
	$("#reportboardcomment_sort").trigger("change");
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
			<button type="button" onclick="location.href='./adminPage_reportboardList?pg=${param.prevPg}'">뒤로가기</button>
			<h1>리뷰 관리</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="reportboardcomment_sort" id="reportboardcomment_sort" onchange="changed()">
						<option value="reportboardcomment_no asc"  <c:if test="${sort eq 'reportboardcomment_no asc'}">selected</c:if>>댓글 번호↑</option>
						<option value="reportboardcomment_no desc" <c:if test="${sort eq 'reportboardcomment_no desc'}">selected</c:if>>댓글 번호↓</option>
						<option value="user_id asc"  <c:if test="${sort eq 'user_id asc'}">selected</c:if>>ID↑</option>
						<option value="user_id desc" <c:if test="${sort eq 'user_id desc'}">selected</c:if>>ID↓</option>
						<option value="reportboardcomment_date asc"  <c:if test="${sort eq 'reportboardcomment_date asc'}">selected</c:if>>작성일↑</option>
						<option value="reportboardcomment_date desc" <c:if test="${sort eq 'reportboardcomment_date desc'}">selected</c:if>>작성일↓</option>
						<option value="reportboardcomment_del asc" <c:if test="${sort eq 'reportboardcomment_del asc'}">selected</c:if>>상태↑</option>
						<option value="reportboardcomment_del desc" <c:if test="${sort eq 'reportboardcomment_del desc'}">selected</c:if>>상태↓</option>
					</select>
				</div>
			</div>
			<table id="reportboardcommentList">
				<thead>
					<tr>
						<th>번호</th>
						<th>ID</th>
						<th>내용</th>
						<th>추가일</th>
						<th>상태</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody id="reportboardcomment_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
			<div class="search">
				<select id="searchColumn">
					<option value="reportboardcomment_comment" ${searchColumn eq 'reportboardcomment_comment' ? 'selected' : ''}>내용</option>
				</select>
				<input type="text" id="searchValue">
				<button type="button" onclick="search()">검색</button>
			</div>
		</div>
	</section>

<script>
	function deleteReportboardcomment(no){
		if(confirm("삭제하겠습니까?")){
			location.href='./adminReportboardcommentDelete?reportboardcomment_no=' + no;
		} else{
			location.href='./adminPage_reportboardcommentList';
		}
	}
	
	function recoveryReportboardcomment(no){
		if(confirm("복구하겠습니까?")){
			location.href='./adminReportboardcommentRecovery?reportboardcomment_no=' + no;
		} else{
			location.href='./adminPage_reportboardcommentList';
		}
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