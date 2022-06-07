<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
<style type="text/css">
.starR1 {
	background:
		url('http://miuu227.godohosting.com/images/icon/ico_review.png')
		no-repeat -52px 0;
	background-size: auto 100%;
	width: 15px;
	height: 30px;
	float: left;
	text-indent: -9999px;
	cursor: pointer;
}

.starR2 {
	background:
		url('http://miuu227.godohosting.com/images/icon/ico_review.png')
		no-repeat right 0;
	background-size: auto 100%;
	width: 15px;
	height: 30px;
	float: left;
	text-indent: -9999px;
	cursor: pointer;
}

.starR1.on {
	background-position: 0 0;
}

.starR2.on {
	background-position: -15px 0;
}
</style>
</head>
<body>
<form action="./reviewupdate" method="post">
	<label>리뷰내용</label> <input type="text" id="review_comment"
		placeholder="내용" name="review_comment" required="required"><br>
	<label>리뷰평가</label>
	<div class="starRev" style="margin: auto; text-align: center;">
		<input type="hidden" value="0.5" id="starRev" name="starRev"
			style="margin: auto; text-align: center;">
		<span class="starR1 on">0.5</span> <span class="starR2">1</span> 
		<span class="starR1">1.5</span> <span class="starR2">2</span>
		<span class="starR1">2.5</span> <span class="starR2">3</span>
		<span class="starR1">3.5</span> <span class="starR2">4</span>
		<span class="starR1">4.5</span> <span class="starR2">5</span>
	</div>
		<input type="hidden" name="review_no" value="${review_no }">
		<input type="hidden" name="restaurant_no" value="${restaurant_no }">
	<button id="yes">확인</button>
</form>
</body>
</html>