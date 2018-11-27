<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码防止重复提交</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/dualLogin">
		用户名:<input name="username" ><br>
		<input name="code"><img alt="" src="${pageContext.request.contextPath }/img"><a href="${pageContext.request.contextPath }/mult/exampleJsp/servlet/dualLogin.jsp">换一换</a>
		<input type="submit" value="提交">
	</form>
</body>
</html>