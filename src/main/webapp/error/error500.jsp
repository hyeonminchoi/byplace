<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% response.setStatus(HttpServletResponse.SC_OK);%>
<html>
<head>
<title>500에러 페이지</title>
</head>
<body>
   서비스 사용에 불편을 끼쳐드려서 대단히 죄송합니다.<br>
   빠른시간내에 문제를 처리하겠습니다.<br>
   <button type="button" onclick="location.href='/byplace/index.jsp'">Home</button>
</body>
</html>