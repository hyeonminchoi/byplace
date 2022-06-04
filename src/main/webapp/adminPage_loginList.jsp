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
<link rel="stylesheet" href="./css/adminPage.css">
<style type="text/css">
	#loginList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#loginList tr, td, th{
		border: 1px solid black;
	}
	#loginList td{
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
function logout(id){
	if(confirm("로그아웃 시키겠습니까?")){
		location.href="./adminUserLogout?user_id="+id;
	}
}
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
	location.href="./adminPage_loginList?" + urlSearch;
}

function changed(){
	var sort = $("#loginList_sort").val();
	var searchColumn = new URLSearchParams(location.search).get("searchColumn");
	var searchValue = new URLSearchParams(location.search).get("searchValue");
	$.ajax({
		url: "./adminPage_loginList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}, "searchColumn":searchColumn, "searchValue": searchValue},
		success: function(loginList){
			$("#loginList_body").empty();
			var temp = "";
			for(var i in loginList){
				temp += "<tr>";
				temp += "	<td>" + loginList[i].user_no + "</td>";
				temp += "	<td>" + loginList[i].user_id + "</td>";
				temp += "	<td>" + loginList[i].user_name + "</td>";
				temp += "	<td>" + loginList[i].user_nickname + "</td>";
				temp += "	<td>" + loginList[i].user_email + "</td>";
				temp += "	<td>" + loginList[i].user_roadAddress + "</td>";
				temp += "	<td>" + loginList[i].user_detailAddress + "</td>";
				temp += "	<td>" + loginList[i].user_extraAddress + "</td>";
				temp += "	<td>" + loginList[i].user_birthday + "</td>";
				temp += "	<td>" + loginList[i].user_joined + "</td>";
				temp += "	<td>" + loginList[i].user_type + "</td>";
				temp += "	<td>" + loginList[i].user_phone + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\'logout(\"" + loginList[i].user_id + "\")\'>강제종료</button>" + "</td>";
				temp += "</tr>";
			}
			$("#loginList_body").append(temp);
			$("#loginList_sort").val(sort).prop("selected",true);
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
	$("#loginList_sort").trigger("change");
}
</script>
</head>
<body>
	<!-- 관리자 권한 있는 사람만 접속 가능 -->
	<c:if test="${sessionScope.USER.user_type ne '관리자' }">
		<script>
			location.href="./index.jsp";
		</script>
	</c:if>
	
	<%@ include file="./menu/adminPageSideBar.jsp"%>
	
	<section class="home-section">
		<div class="home-content">
			<h1>접속중인 사용자</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="loginList_sort" id="loginList_sort" onchange="changed()">
						<option value="user_no asc"  <c:if test="${sort eq 'user_no asc'}">selected</c:if>>사용자 번호↑</option>
						<option value="user_no desc" <c:if test="${sort eq 'user_no desc'}">selected</c:if>>사용자 번호↓</option>
						<option value="user_id asc"  <c:if test="${sort eq 'user_id asc'}">selected</c:if>>ID↑</option>
						<option value="user_id desc" <c:if test="${sort eq 'user_id desc'}">selected</c:if>>ID↓</option>
						<option value="user_nickname asc"  <c:if test="${sort eq 'user_nickname asc'}">selected</c:if>>닉네임↑</option>
						<option value="user_nickname desc" <c:if test="${sort eq 'user_nickname desc'}">selected</c:if>>닉네임↓</option>
						<option value="user_joined asc"  <c:if test="${sort eq 'user_joined asc'}">selected</c:if>>가입일↑</option>
						<option value="user_joined desc" <c:if test="${sort eq 'user_joined desc'}">selected</c:if>>가입일↓</option>
					</select>
				</div>
			</div>
			<table id="loginList">
				<thead>
					<tr>
						<th>번호</th>
						<th>ID</th>
						<th>이름</th>
						<th>닉네임</th>
						<th>이메일</th>
						<th>도로명주소</th>
						<th>세부주소</th>
						<th>추가주소</th>
						<th>생년월일</th>
						<th>가입일</th>
						<th>유형</th>
						<th>전화번호</th>
						<th>강제종료</th>
					</tr>
				</thead>
				<tbody id="loginList_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
			<div class="search">
				<select id="searchColumn">
					<option value="user_id" ${searchColumn eq 'user_id' ? 'selected' : ''}>ID</option>
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