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
	#userLogList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#userLogList tr, td, th{
		border: 1px solid black;
	}
	#userLogList td{
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
	location.href="./adminPage_userlogList?" + urlSearch;
}

function changed(){
	var sort = $("#userLog_sort").val();
	var searchColumn = new URLSearchParams(location.search).get("searchColumn");
	var searchValue = new URLSearchParams(location.search).get("searchValue");
	$.ajax({
		url: "./adminPage_userlogList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}, "searchColumn":searchColumn, "searchValue": searchValue},
		success: function(userlogList){
			$("#userLog_body").empty();
			var temp = "";
			for(var i in userlogList){
				var userLog = JSON.stringify(userlogList[i]);
				temp += "<tr>";
				temp += "	<td>" + userlogList[i].userlog_no + "</td>";
				temp += "	<td>" + userlogList[i].user_id + "</td>";
				temp += "	<td>" + userlogList[i].userlog_status + "</td>";
				temp += "	<td>" + userlogList[i].userlog_date + "</td>";
				temp += "</tr>";
			}
			$("#userLog_body").append(temp);
			$("#userLog_sort").val(sort).prop("selected",true);
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
	$("#userLog_sort").trigger("change");
}
</script>
</head>
<body>
	<!-- 관리자 권한 있는 사람만 접속 가능 -->
	<%-- <c:if test="${sessionScope.userLog.userLog_type ne '관리자' }">
		<script>
			location.href="./index.jsp";
		</script>
	</c:if> --%>
	
	<%@ include file="./menu/adminPageSideBar.jsp"%>
	
	<section class="home-section">
		<div class="home-content">
			<h1>유저리스트</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="userLog_sort" id="userLog_sort" onchange="changed()">
						<option value="userlog_no asc"  <c:if test="${sort eq 'userlog_no asc'}">selected</c:if>>번호↑</option>
						<option value="userlog_no desc" <c:if test="${sort eq 'userlog_no desc'}">selected</c:if>>번호↓</option>
						<option value="user_id asc"  <c:if test="${sort eq 'user_id asc'}">selected</c:if>>ID↑</option>
						<option value="user_id desc" <c:if test="${sort eq 'user_id desc'}">selected</c:if>>ID↓</option>
						<option value="userlog_date asc"  <c:if test="${sort eq 'userlog_date asc'}">selected</c:if>>날짜↑</option>
						<option value="userlog_date desc" <c:if test="${sort eq 'userlog_date desc'}">selected</c:if>>날짜↓</option>
						<option value="userlog_status asc"  <c:if test="${sort eq 'userlog_status asc'}">selected</c:if>>상태↑</option>
						<option value="userlog_status desc" <c:if test="${sort eq 'userlog_status desc'}">selected</c:if>>상태↓</option>
					</select>
				</div>
			</div>
			<table id="userLogList">
				<thead>
					<tr>
						<th>번호</th>
						<th>ID</th>
						<th>상태</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody id="userLog_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
			<div class="search">
				<select id="searchColumn">
					<option value="user_id" ${searchColumn eq 'userLog_id' ? 'selected' : ''}>ID</option>
				</select>
				<input type="text" id="searchValue">
				<button type="button" onclick="search()">검색</button>
			</div>
		</div>
	</section>
<script type="text/javascript">
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