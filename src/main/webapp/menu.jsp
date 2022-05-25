<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <div id="menu">
      <div id="navi">
         <ul id="menulist">
            <li id="mainlogo">BY PLACE</li>
          	<li>
          	<button>로그인</button>
          	</li>
          	<li>
          	<button>회원가입</button>
          	</li>
          
         <% if(session.getAttribute("USER") != null){ %>
            <li>
               <a href="./userInfo.jsp">
                  <img src="./img/id.png">${sessionScope.user_name }HELLO.
               </a>
            </li>
            <li>
               <img src="./img/logout.png" onclick="logout()"></li>
         <%} else { %>
            <li>
               <img src="./img/login.png" onclick="location.href='./login.jsp'"></li>
         <% } %>
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