<%@page import="com.sun.jna.platform.win32.WinUser.MSG"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jstl使用体验</title>
</head>
<body>
	<!-- 
	以下标签使用
	set
	remove
	catch
	out
	 -->
	<c:set var="name" value="zhangshan" scope="page"></c:set>
	<c:remove var="name" scope="page"/>
	<c:out value='<%=pageContext.getAttribute("name") %>'></c:out>
	
	
	<c:catch var="error">
		<c:out value="<%=2/0 %>"></c:out>
	</c:catch>
	<c:out value='<%=((Exception)pageContext.getAttribute("error")).getMessage() %>'></c:out>
	
	
	<%
		Map<String,String> map = new HashMap<String,String>();
	%>
	<c:set target='<%=map %>' property='age' value="12" ></c:set>
	<c:out value='<%=map.get("age") %>'></c:out>
	
	
</body>
</html>