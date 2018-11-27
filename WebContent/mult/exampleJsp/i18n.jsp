<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<fmt:setBundle basename="i18n11.login" var="bundle" scope="page"/>
<fmt:setLocale value="${pageContext.request.locale }" scope="page"/>

<title><fmt:message bundle="${bundle }" key="title" var="tit"></fmt:message></title>
</head>
<body>
<%-- 
	<%
		Locale locale = request.getLocale();
		ResourceBundle resource = ResourceBundle.getBundle("i18n.login", locale);
		System.out.print(resource.getString("title"));
	%>
	 --%>
	<fmt:message bundle="${bundle }" key="loginname"></fmt:message>:<input><br>
	<fmt:message bundle="${bundle }" key="message" ></fmt:message>:<input><br>
	
</body>
</html>