<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String uuid = UUID.randomUUID().toString();
		pageContext.setAttribute("token", uuid);
		pageContext.getSession().setAttribute("token", uuid);
	%>
	<form action="${pageContext.request.contextPath }/doubleSubmitServlet" method="post">
		用户名：<input name="name">
		<input value="${token }" name="token" type="hidden">
		<input type="submit" value="提交">
	</form>
</body>
</html>

