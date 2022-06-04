<%@page import="com.byplace.admin.util.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%LoginManager loginManager = LoginManager.getInstance();	
	%>
	<%=loginManager.getUserCount() %><br>
	<%=loginManager.LoginUserList() %>
</body>
</html>