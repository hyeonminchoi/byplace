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
	#categoryList{
		margin: 0 auto;
		width: 500px;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#categoryList tr, td, th{
		border: 1px solid black;
	}
	#categoryList td{
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
		width: 300px;
		height: 200px;
	}
	dialog button{
		margin: 10px auto;
		padding: 5px;
		width: 80px;
		height: auto;
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
	location.href="./adminPage_categoryList?" + urlSearch;
}
function changed(){
	var sort = $("#category_sort").val();
	var searchColumn = new URLSearchParams(location.search).get("searchColumn");
	var searchValue = new URLSearchParams(location.search).get("searchValue");
	$.ajax({
		url: "./adminPage_categoryList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}, "searchColumn":searchColumn, "searchValue": searchValue},
		success: function(categoryList){
			$("#category_body").empty();
			var temp = "";
			for(var i in categoryList){
				var category = JSON.stringify(categoryList[i]);
				temp += "<tr>";
				temp += "	<td>" + categoryList[i].category_no + "</td>";
				temp += "	<td>" + categoryList[i].category_category + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\'showCategoryEditDialog(" + category + ")\'>수정</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"deleteCategory(" + categoryList[i].category_no + ")\">삭제</button>" + "</td>";
				temp += "</tr>";
			}
			$("#category_body").append(temp);
			$("#category_sort").val(sort).prop("selected",true);
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
	$("#category_sort").trigger("change");
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
			<h1>카테고리 관리</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="category_sort" id="category_sort" onchange="changed()">
						<option value="category_no asc"  <c:if test="${sort eq 'category_no asc'}">selected</c:if>>카테고리 번호↑</option>
						<option value="category_no desc" <c:if test="${sort eq 'category_no desc'}">selected</c:if>>카테고리 번호↓</option>
					</select>
				</div>
				<div>
					<button type="button" onclick="showCategoryAddDialog()">카테고리 추가</button>
				</div>
			</div>
			<table id="categoryList">
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
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
			<div class="search">
				<select id="searchColumn">
					<option value="category_category" ${searchColumn eq 'category_category' ? 'selected' : ''}>카테고리</option>
				</select>
				<input type="text" id="searchValue">
				<button type="button" onclick="search()">검색</button>
			</div>
		</div>
	</section>

<dialog id="categoryAddDialog">
	<div>
		<h1>카테고리 추가</h1>
		<form action="./adminCategoryAdd" method="post">
			<div>
				<label>카테고리 이름</label>
				<input type="text" name="categoryName" required="required">
			</div>
			<div>
				<button type="submit">전송</button>
				<button type="button" onclick="hideCategoryAddDialog()">취소</button>
			</div>
		</form>
	</div>
</dialog>

<dialog id="categoryEditDialog">
	<div>
		<h1>카테고리 수정</h1>
		<form action="./adminCategoryEdit" method="post">
			<input type="hidden" name="categoryNo" id="categoryNo">
			<div>
				<label>카테고리 이름</label>
				<input type="text" name="categoryName" required="required" id="categoryName">
			</div>
			<div>
				<button type="submit">전송</button>
				<button type="button" onclick="hideCategoryEditDialog()">취소</button>
			</div>
		</form>
	</div>
</dialog>

<script>

	function deleteCategory(no){
		if(confirm("삭제하겠습니까?")){
			location.href='./adminCategoryDelete?category_no=' + no;
		} else{
			location.href='./adminPage_categoryList';
		}
	}
	var categoryAddDialog = document.getElementById("categoryAddDialog");
	function showCategoryAddDialog(){
		categoryAddDialog.showModal();
	}
	function hideCategoryAddDialog(){
		categoryAddDialog.close();
	}
	
	var categoryEditDialog = document.getElementById("categoryEditDialog");
	function showCategoryEditDialog(category){
		$("#categoryNo").val(category.category_no);
		$("#categoryName").val(category.category_category);
		categoryEditDialog.showModal();
	}
	function hideCategoryEditDialog(){
		categoryEditDialog.close();
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