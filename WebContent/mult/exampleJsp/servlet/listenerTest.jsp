<%@page import="com.web.example.listener.UserBuldingActivatyListener"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>监听测试页</h1>
	
	
	<%
		//监听serveltContext值变化 
		/* 
		getServletContext().setAttribute("tt", "123");
		getServletContext().setAttribute("tt", "111");
		getServletContext().removeAttribute("tt");
		 */
		//监听session属性值变化
		/* 
		session.setAttribute("tt", "123");
		session.setAttribute("tt", "111");
		//session.removeAttribute("tt");
		//或
		session.invalidate();
		 */
		 /* 
		//监听request属性值变化
		request.setAttribute("tt", "123");
		request.setAttribute("tt", "111");
		request.removeAttribute("tt");//要写删除，不然request响应失效后不会触发
		 */
		
		 
		 UserBuldingActivatyListener user = new UserBuldingActivatyListener();
		 user.setUsername("tt");
		 
		 session.setAttribute("user", user);
		 
		 
	%>
</body>
</html>