<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style type="text/css">
	#findidForm{
		margin:0 auto;
		width: 500px;
		height: 300px;
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
   var phone = form.phone;
   if(phone.value.length == 0){
      alert("전화번호를 입력하세요");
      phone.focus();
      return false;
   }
   
      return false;
   }
</script>
</head>
<body>
<form action="Findid" method="post">
      <table border="0">
         <div id="finidForm" name="findid">
            <tr>
               <th>닉네임</th>
               <td>
                  <input type="text" name="user_nick" required>
               </td>
            </tr>
            <tr>
               <th>전화번호</th>
               <td>
                  <input type="text" name="user_phone" required>
               </td>
            </tr>
         </div>
         <tfoot>
            <tr>
               <td align="center" colspan="2">
                  <input type="submit" value="찾기">
               </td>
            </tr>
         </tfoot>
      </table>
   </form>
   
   
   <!-- error에 대한 처리 -->
   <%if(request.getParameter("error") != null){ %>
   <h6><font color="red">해당하는 정보로 아이디를 찾지 못했습니다</font></h6>
   <%} %>
		
</body>
</html>