<%@ page language="java" pageEncoding="UTF-8"%>
<%
	System.out.println("conenction server success!");
	
	System.out.println(request.getMethod());
	
	String json = "{'province':'jilinsheng'}";
	
	String callback = request.getParameter("callback");
	
	System.out.println(callback);
	
	
	out.println("callback("+json+")");
	
%>