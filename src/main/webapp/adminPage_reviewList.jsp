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
	#reviewList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#reviewList tr, td, th{
		border: 1px solid black;
	}
	#reviewList td{
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
	location.href="./adminPage_reviewList?" + urlSearch;
}
function changed(){
	var sort = $("#review_sort").val();
	var searchColumn = new URLSearchParams(location.search).get("searchColumn");
	var searchValue = new URLSearchParams(location.search).get("searchValue");
	$.ajax({
		url: "./adminPage_reviewList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}, "searchColumn":searchColumn, "searchValue": searchValue, "restaurant_no":${restaurant_no}},
		success: function(reviewList){
			$("#review_body").empty();
			var temp = "";
			for(var i in reviewList){
				var review = JSON.stringify(reviewList[i]);
				temp += "<tr>";
				temp += "	<td>" + reviewList[i].review_no + "</td>";
				temp += "	<td>" + reviewList[i].user_id + "</td>";
				temp += "	<td>" + reviewList[i].review_comment + "</td>";
				temp += "	<td>" + reviewList[i].review_date + "</td>";
				temp += "	<td>" + reviewList[i].review_rating + "</td>";
				if(reviewList[i].review_del == 0){
					temp += "	<td>" + "게시" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\"deleteReview(" + reviewList[i].review_no + ")\">삭제</button>" + "</td>";
				} else if(reviewList[i].review_del == 1){
					temp += "	<td>" + "삭제" + "</td>";
					temp += "	<td>" + "<button type=\"button\" onclick=\"recoveryReview(" + reviewList[i].review_no + ")\">복구</button>" + "</td>";
				}
				temp += "</tr>";
			}
			$("#review_body").append(temp);
			$("#review_sort").val(sort).prop("selected",true);
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
	$("#review_sort").trigger("change");
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
			<button type="button" onclick="location.href='./adminPage_restaurantList?pg=${param.prevPg}'">뒤로가기</button>
			<h1>리뷰 관리</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="review_sort" id="review_sort" onchange="changed()">
						<option value="review_no asc"  <c:if test="${sort eq 'review_no asc'}">selected</c:if>>리뷰 번호↑</option>
						<option value="review_no desc" <c:if test="${sort eq 'review_no desc'}">selected</c:if>>리뷰 번호↓</option>
						<option value="user_id asc"  <c:if test="${sort eq 'review_name asc'}">selected</c:if>>ID↑</option>
						<option value="user_id desc" <c:if test="${sort eq 'review_name desc'}">selected</c:if>>ID↓</option>
						<option value="review_date asc"  <c:if test="${sort eq 'review_joined asc'}">selected</c:if>>추가일↑</option>
						<option value="review_date desc" <c:if test="${sort eq 'review_joined desc'}">selected</c:if>>추가일↓</option>
						<option value="review_rating asc"  <c:if test="${sort eq 'review_joined asc'}">selected</c:if>>별점↑</option>
						<option value="review_rating desc" <c:if test="${sort eq 'review_joined desc'}">selected</c:if>>별점↓</option>
						<option value="review_del asc" <c:if test="${sort eq 'review_del asc'}">selected</c:if>>상태↑</option>
						<option value="review_del desc" <c:if test="${sort eq 'review_del desc'}">selected</c:if>>상태↓</option>
					</select>
				</div>
			</div>
			<table id="reviewList">
				<thead>
					<tr>
						<th>번호</th>
						<th>ID</th>
						<th>내용</th>
						<th>추가일</th>
						<th>별점</th>
						<th>상태</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody id="review_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
			<div class="search">
				<select id="searchColumn">
					<option value="review_comment" ${searchColumn eq 'review_comment' ? 'selected' : ''}>내용</option>
				</select>
				<input type="text" id="searchValue">
				<button type="button" onclick="search()">검색</button>
			</div>
		</div>
	</section>

<script>
	function deleteReview(no){
		if(confirm("삭제하겠습니까?")){
			location.href='./adminReviewDelete?review_no=' + no;
		} else{
			location.href='./adminPage_reviewList';
		}
	}
	
	function recoveryReview(no){
		if(confirm("복구하겠습니까?")){
			location.href='./adminReviewRecovery?review_no=' + no;
		} else{
			location.href='./adminPage_reviewList';
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