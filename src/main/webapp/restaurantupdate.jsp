<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/favicon.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음식점 수정</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">></script>
<script type="text/javascript">

/* function cancel(){
	alert("취소");
	location.href="./index.jsp";
}
function goSubmit(){
	alert("확인");
	location.href="resadd";
} */

function cancel(n1){
	location.href='restaurantdetail?restaurant_no='+n1;
}

</script>
</head>
<body>
	<div id="main">
		<div id="resup" style="text-align: center;">
			<h1>음식점수정</h1>
			<hr>
			<form action="./resup" method="post"  name="resup" enctype="multipart/form-data">
			<div>
			<label>음식점 이름</label> 
			<input type="text" id="restaurant_name" placeholder="음식점 이름을 쓰시오." name="restaurant_name" required="required"><br>
			</div>
			<div>
			<label>음식점 소개</label>
			<input type="text" id="restaurant_description" placeholder="음식점 설명을 쓰시오." name="restaurant_description" required="required"><br>
			</div>
			<div>
			<label>음식점 주소</label>
			<input type="text" id="restaurant_postcode" placeholder="우편번호" name="restaurant_postcode" required="required">
			<input type="button" onclick="restaurant_findpostcode()" value="우편번호 찾기" name="findPostcode"><br>
			<input type="text" id="restaurant_roadAddress" placeholder="도로명주소" name="restaurant_roadAddress" required="required"><br>
			<input type="text" id="restaurant_detailAddress" placeholder="상세주소" name="restaurant_detailAddress"><br>
			<input type="text" id="restaurant_extraAddress" placeholder="참고항목" name="restaurant_extraAddress"><br>
			</div>
			<div id="categ">
			<label>카테고리</label>
			<select name="category_category" id="category_category" required="required">
			<option value="">선택</option>
			<c:forEach items="${catelist }" var="i">
			<option value="${i.category_category }">${i.category_category }</option>
			</c:forEach>
			</select>
			</div>
			<div id="resimg">
			<label>음식점 사진</label>
			<input type="file" name="restaurant_image" id="restaurant_image" accept="image/*" required="required">
			</div>
			<button>확인</button>
			</form>
			<button onclick="cancel(${resdetail.restaurant_no})">취소</button>
		</div>
	</div>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function restaurant_findpostcode() {
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
                document.getElementById('restaurant_postcode').value = data.zonecode;
                document.getElementById("restaurant_roadAddress").value = roadAddr;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("restaurant_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("restaurant_extraAddress").value = '';
                }

            }
        }).open();
    }
</script>
</body>
</html>