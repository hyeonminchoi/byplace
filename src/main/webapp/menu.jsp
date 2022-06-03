<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <div id="menu">
      <div id="navi">
         <ul id="menulist">
            <li id="mainlogo">BY PLACE</li>
           	<c:if test="${empty sessionScope.USER}">
	          	<li class="mainbutton1">
	          		<img src="./img/login.png" onclick="location.href='./login.jsp'">
	          	</li>
	          	<li class="mainbutton2">
	          		<img src="./img/memberjoin.png" onclick="location.href='./join.jsp'">
	          	</li>
          	</c:if>
          	<c:if test="${not empty sessionScope.USER}">
	            <li class="mainbutton1">
	               <img src="./img/logout.png" onclick="logout()">
	           	</li>
         	</c:if>
      	</ul>
      </div>
   </div>
<script type="text/javascript">
function logout(){
   if(      confirm("로그아웃 하시겠습니까?")    ){
      location.href="./logout";
   }
   
}
</script>