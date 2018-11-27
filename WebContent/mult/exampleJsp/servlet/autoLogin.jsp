<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/autoLogin">
		用户：<input name="username" ><br>
		密码：<input name="password" type="password"><br>
		自动登录：<input name="auto" value="1" type="checkbox"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>