<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대표 메뉴 추가</title>
<style type="text/css">
div{
	text-align: center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">></script>
<script type="text/javascript">
$(function(){
	$('#cancel').click(function(){
		location.href = './restaurantdetail?restaurant_no=${r.restaurant_no }';
	});
});
</script>
</head>
<body>
	<div id="main">
		<div id="menuadd">
			<h1>대표 메뉴 추가</h1>
			<hr>
			<form action="./menuadd" method="post" name="menuadd" enctype="multipart/form-data">
			<label>음식 이름</label>
			<input type="text" id="food_name" placeholder="음식 이름을 입력하시오." name="food_name" required="required"><br>
			<label>음식 설명</label>
			<input type="text" id="food_description" placeholder="음식 설명을 입력하시오." name="food_description" required="required"><br>
			<label>음식 가격</label>
			<input type="text" id="food_price" placeholder="음식 가격을 입력하시오." name="food_price" required="required"><br>
			<label>음식 사진</label>
			<input type="file" name="food_image" id="food_image" accept="image/*" required="required"><br>
			<button>확인</button>
			</form>
			<button name="cancel" id="cancel">취소</button>
		</div>
	</div>
</body>
</html>