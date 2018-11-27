<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>下载列表：</h1>
	<c:forEach items="${map }" var="m">
		<c:url value="/downloadServlet" var="url">
			<c:param name="filename" value="${m.value }"></c:param>
			<c:param name="fileurl" value="${m.key }"></c:param>
		</c:url>
		${m.value }<a href="${url }">下载</a><br>
	</c:forEach>
</body>
</html>