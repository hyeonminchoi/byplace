<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#joinbox{
	width: 800px;
	height: 500px;
	margin: 0 auto;
	padding: 0;
	box-sizing: border-box;
	text-align: center;
}
</style>
<script type="text/javascript">

function check() {
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

}

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
				$("#checkResult").css("color", "black");
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
</script>
</head>
</head>
<title>Join Us</title>
<body>
		<div id="joinbox">
		<h1>회원가입</h1>
		<hr>
			<h2>개인정보 입력</h2>
			<form name="joinform" action="./join" method="post" onsubmit="return check()">
			<div>
			<label>아이디</label>
			<input type="text" id="id" name="id" placeholder="아이디를 입력하세요." class="form-control" onchange= "idcheck()">
			</div>
			<button type="submit" id="CheckId" class="btn btn-success">아이디 중복확인</button>
			<div id="checkId">아이디를 확인중입니다.</div>
			<div>
			<label>비밀번호</label>
			<input type="password" name="passwd" placeholder="비밀번호를 입력하세요." class="form-control"><br>
			</div>
			<div>
			<label>비밀번호 확인</label>
			<input type="password" name="passwd" placeholder="비밀번호를 확인하세요." class="form-control"><br>
			</div>
			<div>
			<label>닉네임</label>
			<input type="password" name="passwd" placeholder="닉네임을 입력하세요." class="form-control"><br>
			</div>
			<div>
			<label>전화번호</label>
			<input type="tel" name="tel" placeholder="전화번호를 입력하세요." class="form-control"><br>
			</div>
			<div>
			<label>이메일</label>
			<input type="email" name="email" placeholder="이메일을 입력하세요." class="form-control"><br>
			</div>
			<div>
			<label>주소</label>
			<input type="text" name="addr" placeholder="주소를 입력하세요." class="form-control"><br>
			</div>
			<div>
			<label>생년월일</label>
			<input type="number" name="age" placeholder="생년월일을 입력하세요." class="form-control"><br>
			</div>
			<div>
			<select name="user_type" required="required">
				<option value="개인">개인</option>
				<option value="사업자">사업자</option>
				<option value="관리자">관리자</option>
			</select>
			</div>
			<button type="submit" id="joinBtn" class="btn btn-success" >Join</button>
			<button type="button" onclick="location.href='./index.jsp'">취소하기</button>
			</form>
	</div>

</body>
</html>

