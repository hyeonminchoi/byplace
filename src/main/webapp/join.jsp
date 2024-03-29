<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/favicon.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/join.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

/* function check() {
   var form = document.joinform;
   var id = form.id;
   if(id.value.length == 0){
      alert("아이디를 입력하세요");
      id.focus();
      return false;
   }
   var passwd = form.passwd;
   if(passwd.value.length == 0){
      alert("비밀번호를 입력하세요");
      passwd.focus();
      return false;
   }
   var name = form.name;
   if(name.value.length == 0){
      alert("이름을 입력하세요");
      name.focus();
      return false;
   }
   var age = form.age;
   if(age.value.length == 0){
      alert("나이를 입력하세요");
      age.focus();
      return false;
   }
   var email = form.email;
   if(email.value.length == 0){
      alert("이메일을 입력하세요");
      email.focus();
      return false;
   }
   var tel = form.tel;
   if(tel.value.length == 0){
      alert("전화번호를 입력하세요");
      tel.focus();
      return false;
   }

} */

function idCheck(){
	var id = $("#id").val();
	if(id == "" || id.lenght < 4){
		$("#checkResult").css("color", "red");
		$("#checkResult").text("4글자 이상이어야 함");
	}else{
		$.ajax({
			url : "./idCheck",
			type : "POST",
			dataType : "html",
			data : {"id" : id},
			success : function(data){
				if(data == 0){
					$("#checkResult").css("color", "green");
					$("#checkResult").text(id+"는 가입할 수 있습니다.");
					$("#joinbtn").attr("disabled", false);
				}else{
					$("#checkResult").css("color", "red");
					$("#checkResult").text(id+"는 이미 등록된 ID입니다.");
					$("#joinbtn").attr("disabled", true);
					$("#id").focus();
				}
			},
			error : function(){
				alert("서버가 동작하지 않습니다.");
			}
		});
	}
}
function nicknameCheck(){
	var nickname = $("#nickname").val();
	if(nickname == "" || nickname.lenght < 4){
		$("#checkNicknameResult").css("color", "red");
		$("#checkNicknameResult").text("3글자 이상이어야 함");
	}else{
		$.ajax({
			url : "./nicknameCheck",
			type : "POST",
			dataType : "html",
			data : {"nickname" : nickname},
			success : function(data){
				if(data == 0){
					$("#checkNicknameResult").css("color", "green");
					$("#checkNicknameResult").text(nickname+"는 등록할 수 있습니다.");
					$("#joinbtn").attr("disabled", false);
				}else{
					$("#checkNicknameResult").css("color", "red");
					$("#checkNicknameResult").text(nickname+"는 이미 등록된 닉네임입니다.");
					$("#joinbtn").attr("disabled", true);
					$("#nickname").focus();
				}
			},
			error : function(){
				alert("서버가 동작하지 않습니다.");
			}
		});
	}
}

</script>

<title>Join Us</title>
</head>
<body>
		<c:if test="${not empty error}">
			<script>
				alert("${error}");
			</script>
		</c:if>
		<div id="joinbox">
		<h1>회원가입</h1>
		<hr>
			<h2>개인정보 입력</h2>
			<form name="joinform" id="joinform" action="./join" method="post" onsubmit="return check()">
			<table>
				<tr>
					<td><label>아이디</label></td>
					<td><input type="text" id="id" name="id" placeholder="아이디를 입력하세요." class="form-control" onchange="idCheck()" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3"><div id="checkResult">아이디를 확인중입니다.</div></td>
				</tr>
				<tr>
					<td><label>비밀번호</label></td>
					<td><input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요." class="form-control" required="required"></td>
					<td>
				</tr>
				<tr>
					<td><label>비밀번호 확인</label></td>
					<td><input type="password" id="password1" placeholder="비밀번호를 확인하세요." class="form-control" onchange="pwCheck()" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td><label>이름</label></td>
					<td><input type="text" name="name" placeholder="이름을 입력하세요." class="form-control" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td><label>닉네임</label></td>
					<td><input type="text" name="nickname" id="nickname" placeholder="닉네임을 입력하세요." class="form-control" onchange="nicknameCheck()"  required="required"></td>
				</tr>
				<tr>
					<td colspan="3"><div id="checkNicknameResult">닉네임을 확인중입니다.</div></td>
				</tr>
				<tr>
					<td><label>전화번호</label></td>
					<td><input type="text" id="nickname" name="phone" placeholder="전화번호를 입력하세요." class="form-control" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td><label>이메일</label></td>
					<td><input type="email" name="email" placeholder="이메일을 입력하세요." class="form-control" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right;"><label id="ta">주소</label></td>
					<td>
						<input type="text" name="postcode" id="postcode" placeholder="우편번호" required="required"><br>
						<input type="text" name="roadAddress" id="roadAddress" placeholder="도로명주소" required="required"><br>
						<input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소" required="required"><br>
						<input type="text" name="extraAddress" id="extraAddress" placeholder="참고항목" required="required"><br>
					</td>
					<td valign="top">
						<input id="postcodeBtn" type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
					</td>
				</tr>
				<tr>
					<td><label>생년월일</label></td>
					<td><input type="date" name="birthday" placeholder="생년월일을 입력하세요." class="form-control" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td><label>유형</label></td>
					<td>
						<select name="type" required="required">
							<option value="">유형을 선택하세요.</option>
							<option value="개인">개인</option>
							<option value="사업자">사업자</option>
						</select>
					</td>
					<td></td>
				</tr>
			</table>
			<button type="submit" id="joinBtn">Join</button>
			<button type="button" id="joinBtn" onclick="location.href='./index.jsp'">취소하기</button>
			</form>
			</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
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
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }
            }
        }).open();
    }
</script>
</body>
</html>

