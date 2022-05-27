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
	#userList{
		margin: 0 auto;
		width: 100%;
		border-collapse: collapse;
		border: 1px solid black;
	}
	#userList tr, td, th{
		border: 1px solid black;
	}
	#userList td{
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
function idCheck(){
	var id = $("#user_id").val();
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
	var nickname = $("#user_nickname").val();
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

function changed(){
	var sort = $("#user_sort").val();
	$.ajax({
		url: "./adminPage_userList_JSON",
		type: "GET",
		dataType: "json",
		data : {"sort" : sort, "pageSize" : ${pageSize}, "pg" : ${pg}},
		success: function(userList){
			$("#user_body").empty();
			var temp = "";
			for(var i in userList){
				var user = JSON.stringify(userList[i]);
				temp += "<tr>";
				temp += "	<td>" + userList[i].user_no + "</td>";
				temp += "	<td>" + userList[i].user_id + "</td>";
				temp += "	<td>" + userList[i].user_name + "</td>";
				temp += "	<td>" + userList[i].user_nickname + "</td>";
				temp += "	<td>" + userList[i].user_email + "</td>";
				temp += "	<td>" + userList[i].user_roadAddress + "</td>";
				temp += "	<td>" + userList[i].user_detailAddress + "</td>";
				temp += "	<td>" + userList[i].user_extraAddress + "</td>";
				temp += "	<td>" + userList[i].user_birthday + "</td>";
				temp += "	<td>" + userList[i].user_joined + "</td>";
				temp += "	<td>" + userList[i].user_type + "</td>";
				temp += "	<td>" + userList[i].user_phone + "</td>";
				if(userList[i].user_status == 0){
					temp += "	<td>" + "대기" + "</td>";
				} else if(userList[i].user_status == 1){
					temp += "	<td>" + "승인" + "</td>";
				} else{
					temp += "	<td>" + "블랙" + "</td>";
				}
				if(userList[i].user_del == 0){
					temp += "	<td>" + "N" + "</td>";
				} else{
					temp += "	<td>" + "Y" + "</td>";
				}
				temp += "	<td>" + "<button type=\"button\" onclick=\'showUserEditDialog(" + user + ")\'>수정</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"deleteUser(" + userList[i].user_no + ")\">삭제</button>" + "</td>";
				temp += "	<td>" + "<button type=\"button\" onclick=\"blackList(" + userList[i].user_no + ")\">블랙</button>" + "</td>";
				temp += "</tr>";
			}
			$("#user_body").append(temp);
			$("#user_sort").val(sort).prop("selected",true);
		},
		error: function(){
			alert("서버가 동작하지 않습니다.");
		}
	});
}
window.onload = function(){
	$("#user_sort").trigger("change");
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
			<h1>유저리스트</h1>
			<div>
				<div>
					<label>정렬</label>
					<select name="user_sort" id="user_sort" onchange="changed()">
						<option value="user_no asc"  <c:if test="${sort eq 'user_no asc'}">selected</c:if>>사용자 번호↑</option>
						<option value="user_no desc" <c:if test="${sort eq 'user_no desc'}">selected</c:if>>사용자 번호↓</option>
						<option value="user_id asc"  <c:if test="${sort eq 'user_id asc'}">selected</c:if>>ID↑</option>
						<option value="user_id desc" <c:if test="${sort eq 'user_id desc'}">selected</c:if>>ID↓</option>
						<option value="user_nickname asc"  <c:if test="${sort eq 'user_nickname asc'}">selected</c:if>>닉네임↑</option>
						<option value="user_nickname desc" <c:if test="${sort eq 'user_nickname desc'}">selected</c:if>>닉네임↓</option>
						<option value="user_joined asc"  <c:if test="${sort eq 'user_joined asc'}">selected</c:if>>가입일↑</option>
						<option value="user_joined desc" <c:if test="${sort eq 'user_joined desc'}">selected</c:if>>가입일↓</option>
					</select>
				</div>
			</div>
			<table id="userList">
				<thead>
					<tr>
						<th>번호</th>
						<th>ID</th>
						<th>이름</th>
						<th>닉네임</th>
						<th>이메일</th>
						<th>도로명주소</th>
						<th>세부주소</th>
						<th>추가주소</th>
						<th>생년월일</th>
						<th>가입일</th>
						<th>유형</th>
						<th>전화번호</th>
						<th>상태</th>
						<th>탈퇴</th>
						<th>수정</th>
						<th>삭제</th>
						<th>블랙</th>
					</tr>
				</thead>
				<tbody id="user_body">
					
				</tbody>
			</table>
			<my:pagination pageSize="${pageSize}" recordCount="${recordCount}" queryStringName="pg" />
		</div>
	</section>

<dialog id="userEditDialog">
	<div>
		<h1>사용자 정보 수정</h1>
		<form action="./adminUserEdit" method="post">
 			<div>
 				<label>no</label>
 				<input type="number" name="user_no" id="user_no" readonly="readonly">
 			</div>
			<div>
				<label>아이디</label>
				<input type="text" id="user_id" name="user_id" placeholder="아이디를 입력하세요." class="form-control" onchange="idCheck()" disabled="disabled">
				<div id="checkResult">아이디를 확인중입니다.</div>
			</div>
			<div>
				<label>이름</label>
				<input type="text" id="user_name" name="user_name" placeholder="이름을 입력하세요." class="form-control" required="required">
			</div>
			<div>
				<label>닉네임</label>
				<input type="text" name="user_nickname" id="user_nickname" placeholder="닉네임을 입력하세요." class="form-control" onchange="nicknameCheck()"  required="required">
				<div id="checkNicknameResult">닉네임을 확인중입니다.</div>
			</div>
			<div>
				<label>전화번호</label>
				<input type="text" id="user_phone" name="user_phone" placeholder="전화번호를 입력하세요." class="form-control" required="required">
			</div>
			<div>
				<label>이메일</label>
				<input type="email" name="user_email" id="user_email" placeholder="이메일을 입력하세요." class="form-control" required="required">
			</div>
			<div>
				<label>주소</label>
				<input type="text" name="user_postcode" id="user_postcode" placeholder="우편번호" required="required">
				<input type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" name="user_roadAddress" id="user_roadAddress" placeholder="도로명주소" required="required"><br>
				<input type="text" name="user_detailAddress" id="user_detailAddress" placeholder="상세주소" required="required"><br>
				<input type="text" name="user_extraAddress" id="user_extraAddress" placeholder="참고항목" required="required">
			</div>
			<div>
				<label>생년월일</label>
				<input type="date" id="user_birthday" name="user_birthday" placeholder="생년월일을 입력하세요." class="form-control" required="required">
			</div>
			<div>
				<label>유형</label>
				<select name="user_type" id="user_type" required="required">
					<option value="">유형을 선택하세요.</option>
					<option value="개인">개인</option>
					<option value="사업자">사업자</option>
				</select>
			</div>
			<div>
				<button type="submit">수정</button>
				<button type="button" onclick="hideUserEditDialog()">취소</button>
			</div>
		</form>
	</div>
</dialog>

<script>

	function deleteUser(no){
		if(confirm("삭제하겠습니까?")){
			location.href='./adminUserDelete?user_no=' + no;
		} else{
			location.href='./adminPage_userList';
		}
	}
	
	function blackList(no){
		if(confirm("사용자를 블랙리스트에 추가하겠습니까?")){
			location.href='./adminBlackListAdd?user_no=' + no;
		} else{
			location.href='./adminPage_userList';
		}
	}
	
	var userEditDialog = document.getElementById("userEditDialog");
	function showUserEditDialog(user){
		$("#user_no").val(user.user_no);
		$("#user_id").val(user.user_id);
		$("#user_name").val(user.user_name);
		$("#user_nickname").val(user.user_nickname);
		$("#user_phone").val(user.user_phone);
		$("#user_email").val(user.user_email);
		$("#user_postcode").val(user.user_postcode);
		$("#user_roadAddress").val(user.user_roadAddress);
		$("#user_detailAddress").val(user.user_detailAddress);
		$("#user_extraAddress").val(user.user_extraAddress);
		$("#user_birthday").val(user.user_birthday);
		$("#user_type").val(user.user_type);
		userEditDialog.showModal();
	}
	function hideUserEditDialog(){
		userEditDialog.close();
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