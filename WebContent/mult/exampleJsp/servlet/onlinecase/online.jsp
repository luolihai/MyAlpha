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
	<h3>在线页面</h3><br>
	<c:forEach items="${applicationScope.map }" var="m">
		<c:url value="/unactivitySessionServlet" var="url">
			<c:param name="username" value="${m.key }"></c:param>
		</c:url>
		
		${m.key }<a href="${url }">踢掉你</a><br>
		
	</c:forEach>

</body>
</html>