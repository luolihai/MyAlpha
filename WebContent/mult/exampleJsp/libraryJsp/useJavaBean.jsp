<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>javaBean体验</title>
</head>
<body>
	<jsp:useBean id="studen" class="com.javaproject.utilcollect.bean.Studen" scope="page"></jsp:useBean>
<%-- 	<jsp:setProperty property="name" name="studen" value="12"/>	单个参数set值 --%>
	<jsp:setProperty property="*" name="studen"/>	<%-- 多个参数Set值，url传值,可于后台看 --%>
	<%=studen.getName() %>
</body>
</html>