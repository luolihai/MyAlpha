<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/helloCustmoTag.tld" prefix="jpn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义标签</title>
</head>
<body>
	<jpn:hello></jpn:hello>
	<jpn:hello></jpn:hello>
	<jpn:hello></jpn:hello>
	
	<%
		JspWriter ou = pageContext.getOut();
		ou.write("is ok");
	%>
</body>
</html>