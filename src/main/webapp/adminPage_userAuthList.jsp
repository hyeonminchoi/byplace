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
	#userList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#userList tr, td, th{
		border: 1px solid black;
	}
	#userList td{
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
function changed(){
	var sort = $("#user_sort").val();
	$.ajax({
		url: "./adminPage_userAuthList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}},
		success: function(userList){
			$("#user_body").empty();
			var temp = "";
			for(var i in userList){
				var user = JSON.stringify(userList[i]);
				temp += "<tr>";
				temp += "	<td>" + userList[i].user_no + "</td>";
				temp += "	<td>" + userList[i].user_id + "</td>";
				temp += "	<td>" + userList[i].user_name + "</td>";
				temp += "	<td>" + userList[i].user_nickname + "</td>";
				temp += "	<td>" + userList[i].user_email + "</td>";
				temp += "	<td>" + userList[i].user_roadAddress + "</td>";
				temp += "	<td>" + userList[i].user_detailAddress + "</td>";
				temp += "	<td>" + userList[i].user_extraAddress + "</td>";
				temp += "	<td>" + userList[i].user_birthday + "</td>";
				temp += "	<td>" + userList[i].user_joined + "</td>";
				temp += "	<td>" + userList[i].user_type + "</td>";
				temp += "	<td>" + userList[i].user_phone + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\'accept(" + userList[i].user_no + ")\'>승인</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"refuse(" + userList[i].user_no + ")\">거부</button>" + "</td>";
				temp += "</tr>";
			}
			$("#user_body").append(temp);
			$("#user_sort").val(sort).prop("selected",true);
		},
		error: function(){
			alert("서버가 동작하지 않습니다.");
		}
	});
}
window.onload = function(){
	$("#user_sort").trigger("change");
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
			<h1>사용자 권한 승인</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="user_sort" id="user_sort" onchange="changed()">
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
			<table id="userList">
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
						<th>승인</th>
						<th>거부</th>
					</tr>
				</thead>
				<tbody id="user_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
		</div>
	</section>

<script>

	function accept(no){
		if(confirm("권한을 승인하겠습니까?")){
			location.href='./adminUserAccept?user_no=' + no;
		} else{
			location.href='./adminPage_userAuthList';
		}
	}
	
	function refuse(no){
		if(confirm("권한을 거부하겠습니까?")){
			location.href='./adminUserRefuse?user_no=' + no;
		} else{
			location.href='./adminPage_userAuthList';
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