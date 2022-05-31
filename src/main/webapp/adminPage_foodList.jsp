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
	#restaurantList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#restaurantList tr, td, th{
		border: 1px solid black;
	}
	#restaurantList td{
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
	location.href="./adminPage_foodList?" + urlSearch;
}
function changed(){
	var sort = $("#food_sort").val();
	var searchColumn = new URLSearchParams(location.search).get("searchColumn");
	var searchValue = new URLSearchParams(location.search).get("searchValue");
	$.ajax({
		url: "./adminPage_foodList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}, "searchColumn":searchColumn, "searchValue": searchValue, "restaurant_no":${restaurant_no}},
		success: function(foodList){
			$("#food_body").empty();
			var temp = "";
			for(var i in foodList){
				var food = JSON.stringify(foodList[i]);
				temp += "<tr>";
				temp += "	<td>" + foodList[i].food_no + "</td>";
				temp += "	<td>" + foodList[i].food_name + "</td>";
				temp += "	<td>" + foodList[i].food_price + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"showFoodImage(" + foodList[i].food_no + ",\'" + foodList[i].food_image + "\')\">사진보기</button>" + "</td>";
				temp += "	<td>" + foodList[i].food_joined + "</td>";
				temp += "	<td>" + foodList[i].food_description + "</td>";
				if(foodList[i].food_del == 0){
					temp += "	<td>" + "게시" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\'showFoodEditDialog(" + food + ")\'>수정</button>" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\"deleteFood(" + foodList[i].food_no + ")\">삭제</button>" + "</td>";
				} else if(foodList[i].food_del == 1){
					temp += "	<td>" + "삭제" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\'showFoodEditDialog(" + food + ")\'>수정</button>" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\"recoveryFood(" + foodList[i].food_no + ")\">복구</button>" + "</td>";
				}
				temp += "</tr>";
			}
			$("#food_body").append(temp);
			$("#food_sort").val(sort).prop("selected",true);
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
	$("#food_sort").trigger("change");
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
			<button type="button" onclick="window.history.go(-2)">뒤로가기</button>
			<h1>대표메뉴 관리</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="food_sort" id="food_sort" onchange="changed()">
						<option value="food_no asc"  <c:if test="${sort eq 'food_no asc'}">selected</c:if>>메뉴 번호↑</option>
						<option value="food_no desc" <c:if test="${sort eq 'food_no desc'}">selected</c:if>>메뉴 번호↓</option>
						<option value="food_name asc"  <c:if test="${sort eq 'food_name asc'}">selected</c:if>>이름↑</option>
						<option value="food_name desc" <c:if test="${sort eq 'food_name desc'}">selected</c:if>>이름↓</option>
						<option value="food_price asc"  <c:if test="${sort eq 'food_price asc'}">selected</c:if>>가격↑</option>
						<option value="food_price desc" <c:if test="${sort eq 'food_price desc'}">selected</c:if>>가격↓</option>
						<option value="food_joined asc"  <c:if test="${sort eq 'food_joined asc'}">selected</c:if>>추가일↑</option>
						<option value="food_joined desc" <c:if test="${sort eq 'food_joined desc'}">selected</c:if>>추가일↓</option>
						<option value="food_del asc" <c:if test="${sort eq 'food_del asc'}">selected</c:if>>상태↑</option>
						<option value="food_del desc" <c:if test="${sort eq 'food_del desc'}">selected</c:if>>상태↓</option>
					</select>
				</div>
			</div>
			<table id="restaurantList">
				<thead>
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>가격</th>
						<th>이미지</th>
						<th>추가일</th>
						<th>설명</th>
						<th>상태</th>
						<th>수정</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody id="food_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
			<div class="search">
				<select id="searchColumn">
					<option value="food_name" ${searchColumn eq 'food_name' ? 'selected' : ''}>이름</option>
					<option value="food_description" ${searchColumn eq 'food_description' ? 'selected' : ''}>설명</option>
				</select>
				<input type="text" id="searchValue">
				<button type="button" onclick="search()">검색</button>
			</div>
		</div>
	</section>

<dialog id="foodEditDialog">
	<div>
		<h1>대표메뉴 정보 수정</h1>
		<form action="./adminFoodEdit" method="post">
			<input type="hidden" name="restaurant_no" id="restaurant_no">
 			<div>
 				<label>no</label>
 				<input type="number" name="food_no" id="food_no" readonly="readonly">
 			</div>
			<div>
				<label>이름</label>
				<input type="text" id="food_name" name="food_name"  required="required">
			</div>
			<div>
				<label>가격</label>
				<input type="number" id="food_price" name="food_price"  required="required">
			</div>
			<div>
				<label>설명</label>
				<textarea id="food_description" name="food_description"  required="required"></textarea>
			</div>
			<div>
				<button type="submit">수정</button>
				<button type="button" onclick="hideFoodEditDialog()">취소</button>
			</div>
		</form>
	</div>
</dialog>

<dialog id="foodDescriptionDialog">
	<div>
		<textarea id="description" readonly="readonly"></textarea>
	</div>
	<button type="button" onclick="hideFoodDescription()">닫기</button>
</dialog>

<dialog id="foodImageDialog">
	<div>
		<img id="image" alt="로딩 실패">
	</div>
	<button type="button" onclick="showFoodImageEdit()">수정</button>
	<button type="button" onclick="hideFoodImage()">닫기</button>
</dialog>

<dialog id="foodImageEditDialog">
	<form action="./adminFoodImageEdit" method="post" enctype="multipart/form-data">
		<input type="hidden" name="food_no" id="no">
		<div>
			<input type="file" name="food_image">
		</div>
		<button type="submit">변경</button>
		<button type="button" onclick="hideFoodImageEdit()">닫기</button>
	</form>
</dialog>

<script>
	function deleteFood(no){
		if(confirm("삭제하겠습니까?")){
			location.href='./adminFoodDelete?food_no=' + no;
		} else{
			location.href='./adminPage_foodList';
		}
	}
	
	function recoveryFood(no){
		if(confirm("복구하겠습니까?")){
			location.href='./adminFoodRecovery?food_no=' + no;
		} else{
			location.href='./adminPage_foodList';
		}
	}
	
	var foodDescriptionDialog = document.getElementById("foodDescriptionDialog");
	function showFoodDescription(description){
		$("#description").val(description);
		foodDescriptionDialog.showModal();
	}
	
	function hideFoodDescription(){
		foodDescriptionDialog.close();		
	}
	
	var foodImageDialog = document.getElementById("foodImageDialog");
	function showFoodImage(no, image){
		$("#no").attr("value", no);
		$("#image").attr("src", "./foodImage/" + image);
		foodImageDialog.showModal();
	}
	
	function hideFoodImage(){
		foodImageDialog.close();		
	}
	
	var foodImageEditDialog = document.getElementById("foodImageEditDialog");
	function showFoodImageEdit(){
		foodImageEditDialog.showModal();
	}
	
	function hideFoodImageEdit(){
		foodImageEditDialog.close();		
	}
	
	var foodEditDialog = document.getElementById("foodEditDialog");
	function showFoodEditDialog(food){
		$("#restaurant_no").val(food.restaurant_no);
		$("#food_no").val(food.food_no);
		$("#food_name").val(food.food_name);
		$("#food_price").val(food.food_price);
		$("#food_description").val(food.food_description);
		foodEditDialog.showModal();
	}
	
	function hideFoodEditDialog(){
		foodEditDialog.close();
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