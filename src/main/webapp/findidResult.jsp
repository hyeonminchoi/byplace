<%@page import="com.byplace.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기 결과</title>
<style type="text/css">

</style>
</head>
<body>
	<div id="result">
	<c:if test="${not empty user_id }">
		<h4>  회원님의 아이디는 </h4>  
		<div class ="findid">${user_id}</div>
		<h4>  입니다 </h4>
	</c:if>
	<c:if test="${not empty error}">
		${error }
	</c:if>
   	</div>
     
      
</body>
</html>