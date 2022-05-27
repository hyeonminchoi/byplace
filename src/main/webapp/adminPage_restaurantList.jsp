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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function changed(){
	var sort = $("#restaurant_sort").val();
	$.ajax({
		url: "./adminPage_restaurantList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}},
		success: function(restaurantList){
			$("#restaurant_body").empty();
			var temp = "";
			for(var i in restaurantList){
				var restaurant = JSON.stringify(restaurantList[i]);
				temp += "<tr>";
				temp += "	<td>" + restaurantList[i].restaurant_no + "</td>";
				temp += "	<td>" + restaurantList[i].restaurant_name + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\'showRestaurantDescription(\'" + restaurantList[i].restaurant_description + "\')\'>설명보기</button>" + "</td>";
				temp += "	<td>" + restaurantList[i].restaurant_roadAddress + "</td>";
				temp += "	<td>" + restaurantList[i].restaurant_detailAddress + "</td>";
				temp += "	<td>" + restaurantList[i].restaurant_extraAddress + "</td>";
				temp += "	<td>" + restaurantList[i].category_category + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\'showRestaurantImage(\'" + restaurantList[i].restaurant_image + "\')\'>사진보기</button>" + "</td>";
				temp += "	<td>" + restaurantList[i].restaurant_joined + "</td>";
				temp += "	<td>" + restaurantList[i].user_id + "</td>";
				if(restaurantList[i].restaurant_del == 0){
					temp += "	<td>" + "N" + "</td>";
				} else{
					temp += "	<td>" + "Y" + "</td>";
				}
				temp += "	<td>" + "<button type=\"button\" onclick=\'showRestaurantEditDialog(" + restaurant + ")\'>수정</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"deleteRestaurant(" + restaurantList[i].restaurant_no + ")\">삭제</button>" + "</td>";
				temp += "</tr>";
			}
			$("#restaurant_body").append(temp);
			$("#restaurant_sort").val(sort).prop("selected",true);
		},
		error: function(){
			alert("서버가 동작하지 않습니다.");
		}
	});
}
window.onload = function(){
	$("#restaurant_sort").trigger("change");
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
			<h1>음식점리스트</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="restaurant_sort" id="restaurant_sort" onchange="changed()">
						<option value="restaurant_no asc"  <c:if test="${sort eq 'restaurant_no asc'}">selected</c:if>>음식점 번호↑</option>
						<option value="restaurant_no desc" <c:if test="${sort eq 'restaurant_no desc'}">selected</c:if>>음식점 번호↓</option>
						<option value="restaurant_name asc"  <c:if test="${sort eq 'restaurant_name asc'}">selected</c:if>>이름↑</option>
						<option value="restaurant_name desc" <c:if test="${sort eq 'restaurant_name desc'}">selected</c:if>>이름↓</option>
						<option value="category_category asc"  <c:if test="${sort eq 'category_category asc'}">selected</c:if>>카테고리↑</option>
						<option value="category_category desc" <c:if test="${sort eq 'category_category desc'}">selected</c:if>>카테고리↓</option>
						<option value="restaurant_joined asc"  <c:if test="${sort eq 'restaurant_joined asc'}">selected</c:if>>가입일↑</option>
						<option value="restaurant_joined desc" <c:if test="${sort eq 'restaurant_joined desc'}">selected</c:if>>가입일↓</option>
					</select>
				</div>
			</div>
			<table id="restaurantList">
				<thead>
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>설명</th>
						<th>도로명주소</th>
						<th>세부주소</th>
						<th>추가주소</th>
						<th>카테고리</th>
						<th>이미지</th>
						<th>가입일</th>
						<th>글쓴이</th>
						<th>삭제여부</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody id="restaurant_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
		</div>
	</section>

<dialog id="restaurantEditDialog">
	<div>
		<h1>음식점 정보 수정</h1>
		<form action="./adminRestaurantEdit" method="post" enctype="multipart/form-data">
 			<div>
 				<label>no</label>
 				<input type="number" name="restaurant_no" id="restaurant_no" readonly="readonly">
 			</div>
			<div>
				<label>이름</label>
				<input type="text" id="restaurant_name" name="restaurant_name"  required="required">
			</div>
			<div>
				<label>설명</label>
				<textarea id="restaurant_description" name="restaurant_description"  required="required"></textarea>
			</div>
			<div>
				<label>주소</label>
				<input type="text" name="restaurant_postcode" id="restaurant_postcode" placeholder="우편번호" required="required">
				<input type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" name="restaurant_roadAddress" id="restaurant_roadAddress" placeholder="도로명주소" required="required"><br>
				<input type="text" name="restaurant_detailAddress" id="restaurant_detailAddress" placeholder="상세주소" required="required"><br>
				<input type="text" name="restaurant_extraAddress" id="restaurant_extraAddress" placeholder="참고항목" required="required">
			</div>
			<div>
				<label>카테고리</label>
				<select name="category_category" id="category_category">
					<c:forEach var="i" items="${categoryList}">
						<option>카테고리를 선택하세요.</option>
						<option value="${i.category_category}">${i.category_category}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label>이미지</label>
				<input type="file" id="restaurant_image" name="restaurant_image">
			</div>
			<div>
				<button type="submit">수정</button>
				<button type="button" onclick="hideRestaurantEditDialog()">취소</button>
			</div>
		</form>
	</div>
</dialog>

<dialog id="restaurantDescriptionDialog">
	<div>
		<textarea id="restaurant_description"></textarea>
	</div>
	<button type="button" onclick="hideRestaurantDescription()">닫기</button>
</dialog>

<script>

	function deleteRestaurant(no){
		if(confirm("삭제하겠습니까?")){
			location.href='./adminRestaurantDelete?user_no=' + no;
		} else{
			location.href='./adminPage_restaurantList';
		}
	}
	
	var restaurantDescriptionDialog = document.getElementById("restaurantDescriptionDialog");
	function showRestaurantDescription(description){
		$("#restaurant_description").val(description);
		restaurantDescriptionDialog.showModal();
	}
	
	function hideRestaurantDescription(){
		restaurantDescriptionDialog.close();		
	}
	
	var restaurantEditDialog = document.getElementById("restaurantEditDialog");
	function showRestaurantEditDialog(restaurant){
		$("#restaurant_no").val(restaurant.restaurant_no);
		$("#restaurant_name").val(restaurant.restaurant_name);
		$("#restaurant_description").val(restaurant.restaurant_description);
		$("#restaurant_postcode").val(restaurant.restaurant_postcode);
		$("#restaurant_roadAddress").val(restaurant.restaurant_roadAddress);
		$("#restaurant_detailAddress").val(restaurant.restaurant_detailAddress);
		$("#restaurant_extraAddress").val(restaurant.restaurant_extraAddress);
		$("#category_category").val(restaurant.category_category);
		restaurantEditDialog.showModal();
	}
	
	function hideRestaurantEditDialog(){
		restaurantEditDialog.close();
	}
	
	let arrow = document.querySelectorAll(".arrow");
	for (var i = 0; i < arrow.length; i++) {
	  arrow[i].addEventListener("click", (e)=>{
	 	let arrowParent = e.target.parentElement.parentElement;
	 	arrowParent.classList.toggle("showMenu");
	  });
	}	
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function DaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('user_postcode').value = data.zonecode;
                document.getElementById("user_roadAddress").value = roadAddr;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("user_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("user_extraAddress").value = '';
                }
            }
        }).open();
    }
</script>
</body>
</html>