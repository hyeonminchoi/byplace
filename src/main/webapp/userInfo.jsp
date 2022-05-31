<%@page import="com.byplace.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script type="text/javascript">
	//function check(){
	//	if(document.password.value == ""){
	//		alert
	//	}
	//}
	function a(){
		if(confirm("탈퇴하시겠습니까?")){
			location.href='./userquit?user_no=${sessionScope.USER.user_no}';
		}
	}
</script>
</head>
<body>
	<h1>개인정보</h1>
	<hr>
		아이디 : ${sessionScope.USER.user_id } <br>
		이름 : ${sessionScope.USER.user_name } <br>
		닉네임 : ${sessionScope.USER.user_nickname } <br>
		전화번호 : ${sessionScope.USER.user_phone }<br>  
		이메일 : ${sessionScope.USER.user_email}<br>
		우편번호 : ${sessionScope.USER.user_postcode }<br>
		도로명 주소 : ${sessionScope.USER.user_roadAddress }<br>
		상세 주소 : ${sessionScope.USER.user_detailAddress }
			${sessionScope.USER.user_extraAddress }<br>
	<button type="button" onclick="showDialog()">수정하기</button>
	<button type="button" onclick="a()">탈퇴하기</button>
	
<dialog id="dialog">
	<form action="./userInfofix" method="post">
		<div>
			<label>no</label>
			<input type="number" name="user_no" id="user_no" readonly="readonly" value="${sessionScope.USER.user_no }">
		</div>
		<div>
           <label>아이디</label>
           <input type="text" id="user_id" name="user_id" placeholder="아이디를 입력하세요." class="form-control" onchange="idCheck()" disabled="disabled" value="${sessionScope.USER.user_id }">
        </div>
        <div>
           <label>이름</label>
           <input type="text" id="user_name" name="user_name" placeholder="이름을 입력하세요." class="form-control" required="required" value="${sessionScope.USER.user_name }">
        </div>
        <div>
           <label>닉네임</label>
           <input type="text" name="user_nickname" id="user_nickname" placeholder="닉네임을 입력하세요." class="form-control" onchange="nicknameCheck()"  required="required" value="${sessionScope.USER.user_nickname }">
           <div id="checkNicknameResult">닉네임을 확인중입니다.</div>
        </div>
        <div>
        	<label>전화번호</label>
        	<input type="text" id="user_phone" name="user_phone" placeholder="전화번호를 입력하세요." class="form-control" required="required" value="${sessionScope.USER.user_phone }">
        </div>
        <div>
           <label>이메일</label>
           <input type="email" name="user_email" id="user_email" placeholder="이메일을 입력하세요." class="form-control" required="required" value="${sessionScope.USER.user_email }">
        </div>
        <div>
           <label>주소</label>
           <input type="text" name="user_postcode" id="user_postcode" placeholder="우편번호" required="required" value="${sessionScope.USER.user_postcode }">
           <input type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
           <input type="text" name="user_roadAddress" id="user_roadAddress" placeholder="도로명주소" required="required" value="${sessionScope.USER.user_roadAddress }"><br>
           <input type="text" name="user_detailAddress" id="user_detailAddress" placeholder="상세주소" required="required" value="${sessionScope.USER.user_detailAddress }"><br>
           <input type="text" name="user_extraAddress" id="user_extraAddress" placeholder="참고항목" required="required" value="${sessionScope.USER.user_extraAddress }">
        </div>
        <div>
           <label>생년월일</label>
           <input type="date" id="user_birthday" name="user_birthday" placeholder="생년월일을 입력하세요." class="form-control" required="required" value="${sessionScope.USER.user_birthday }">
        </div>
        <div>
           <label>유형</label>
           <select name="user_type" id="user_type" required="required">
              <option value="">유형을 선택하세요.</option>
              <option value="개인" ${sessionScope.USER.user_type eq '개인' ? 'selected' : ''}>개인</option>
              <option value="사업자" ${sessionScope.USER.user_type eq '사업자' ? 'selected' : ''}>사업자</option>
           </select>
		</div>
		<button type="submit" >수정</button>
		<button type="button" onclick="hideDialog()">닫기</button>
	</form>
</dialog>

<script type="text/javascript">
	var dialog = document.getElementById("dialog");
	function showDialog(){
		dialog.showModal();
	}
	function hideDialog(){
		dialog.close();
	}
</script>
</body>
</html>