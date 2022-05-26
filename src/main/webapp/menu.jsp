<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <div id="menu">
      <div id="navi">
         <ul id="menulist">
            <li id="mainlogo">BY PLACE</li>
          	<li id="mainbutton1">
          		<img src="./img/login.png" onclick="location.href='./login.jsp'">
          	</li>
          	<li id="mainbutton2">
          		<img src="./img/memberjoin.png" onclick="location.href='./join.jsp'">
          	</li>
          
         <% if(session.getAttribute("USER") != null){ %>
            <li>
               <a href="./userInfo.jsp">
                  <img src="./img/id.png">${sessionScope.user_name }HELLO.
               </a>
            </li>
            <li>
               <img src="./img/logout.png" onclick="logout()"></li>
         <%}%>
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