<%@ page language="java" pageEncoding="UTF-8"%>
<%
	System.out.println("connection server success!");
	
	System.out.println(request.getMethod());
	
	System.out.println("username = "+request.getParameter("username"));
	System.out.println("password = "+request.getParameter("psw"));
	
	out.println("helloworld!");
	
%>