<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div id="menu">
		<div id="navi">
			<ul>
				<li>
					<a href="./index"><img alt="logo" src="./img/logo.png"></a>
				</li>
				<li>
					<a href="./notice"><img src="./img/notice.png">공지사항</a>
				</li>
				<li>
					<a href="./board"><img src="./img/board.png">보드</a>
				</li>
			<% if(session.getAttribute("USER") != null){ %>
				<li>
					<a href="./userInfo">
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