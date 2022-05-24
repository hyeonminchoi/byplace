<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="./css/adminPage.css">
<style type="text/css">
	table{
		margin: 0 auto;
		width: 500px;
		border-collapse: collapse;
		border: 1px solid black;
	}
	tr, td, th{
		border: 1px solid black;
	}
	td{
		text-align: center;
	}  
	table.pagination { border-collapse: collapse; margin-top: 20px; }
	table.pagination td { text-align: center; border: 1px solid #aaa; }
	table.pagination a { text-decoration: none; display: inline-block; padding: 5px 10px; color: #06C; }   
	table.pagination td.active { background-color: #06C; }
	table.pagination td.active a { color: white; }
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

function sort(){
	var category_sort = $("#category_sort").val();
	$.ajax({
		url: "./adminPage_categoryList",
		type: "GET",
		dataType: "json",
		data : {"category_sort" : category_sort},
		success: function(categoryList){
			$("#category_body").empty();
			var temp = "";
			for(var i in categoryList){
				temp += "<tr>";
				temp += "	<td>" + categoryList[i].category_no + "</td>";
				temp += "	<td>" + categoryList[i].category_category + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"location.href='./editCategory?category_no=" + categoryList[i].category_no + "'\">수정</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"location.href='./deleteCategory?category_no=" + categoryList[i].category_no + "'\">삭제</button>" + "</td>";
				temp += "</tr>";
			}
			$("#category_body").append(temp);
		},
		error: function(){
			alert("서버가 동작하지 않습니다.");
		}
	});
}
window.onload = function(){
	$("#category_sort").trigger("change");
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
		<i class='bx bx-menu'></i>
		<div class="home-content">
			<h1>카테고리 관리</h1>
			<div>
				<label>정렬</label>
				<select id="category_sort" onchange="sort()">
					<option value="category_asc" selected="selected">카테고리 번호↑</option>
					<option value="category_desc">카테고리 번호↓</option>
				</select>
			</div>
			<table>
				<thead>
					<tr>
						<th>카테고리 번호</th>
						<th>카테고리 이름</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody id="category_body">
					
				</tbody>
			</table>
		</div>
	</section>


<script>
	let arrow = document.querySelectorAll(".arrow");
	for (var i = 0; i < arrow.length; i++) {
	  arrow[i].addEventListener("click", (e)=>{
	 	let arrowParent = e.target.parentElement.parentElement;
	 	arrowParent.classList.toggle("showMenu");
	  });
	}
	let sidebar = document.querySelector(".sidebar");
	let sidebarBtn = document.querySelector(".bx-menu");
	console.log(sidebarBtn);
	sidebarBtn.addEventListener("click", ()=>{
	  sidebar.classList.toggle("close");
	});
</script>
</body>
</html>