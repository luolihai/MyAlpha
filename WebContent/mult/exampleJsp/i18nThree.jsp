<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<fmt:setBundle basename="i18n11.login" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}" scope="page"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="">
		<fmt:message bundle="${bundle}" key="loginname"></fmt:message>：<input name="username"><br>
		<fmt:message bundle="${bundle}" key="message"></fmt:message>：<input name="password"><br>
		<input type="submit" value="提交">
	</form>

</body>
</html>