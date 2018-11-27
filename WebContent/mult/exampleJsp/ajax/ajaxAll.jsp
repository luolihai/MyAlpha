<%@ page language="java" pageEncoding="UTF-8"%>
<%
	System.out.println("conenction server success!");
	
	System.out.println(request.getMethod());
	
	System.out.println("username = "+request.getParameter("username"));
	System.out.println("password = "+request.getParameter("psw"));
	
	//响应文本格式
// 	out.println("helloworld!");
	
	/* 
	//响应XML格式
	response.setContentType("text/xml");
	out.println("<china><province name='jilinsheng'></province></china>");
	 */
	 
	//响应JSON格式:jQuery提供的get()或post()方法时,数据格式为JSON格式时,只能使用第三方工具构建JSON格式的数据内容,不能使用手工方式
	out.println("[{'province':'jilinsheng'},{'province':'liaoningsheng'},{'province':'shandongsheng'}]");
	
%>