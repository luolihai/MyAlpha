<%@page import="com.web.example.domain.Calculate"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>el表达式A</title>
</head>
<body>

	<%
		int a = 1;
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		map.put("m1", "m1");
		map.put("m2", "m2");
		map.put("m3", "m3");
		String[] strs = {"1","2","3"};
 		pageContext.setAttribute("a", a,PageContext.PAGE_SCOPE);
		pageContext.setAttribute("list", list,PageContext.PAGE_SCOPE);
		pageContext.setAttribute("map", map,PageContext.PAGE_SCOPE);
		pageContext.setAttribute("strs", strs,PageContext.PAGE_SCOPE);
		
		LinkedHashMap<String,Calculate> objectMap = new LinkedHashMap<String,Calculate>();
		Calculate a1 = new Calculate(1,1,'1',1);
		Calculate b1 = new Calculate(2,2,'2',2);
		Calculate c1 = new Calculate(3,3,'3',3);
		
		objectMap.put("a1", a1);
		objectMap.put("b1", b1);
		objectMap.put("c1", c1);
		pageContext.setAttribute("objectMap", objectMap,PageContext.PAGE_SCOPE);
		
	%>

	${"123" }
	<br>
	${1>2?"大于":"小于" }
	<br>
	<!--a没值一样没报错 -->
	${a+1}
	<br>
	
	${list}
	${list[0]}
	${list["0"]}
	<br>
	${map }
	${map.m1 }
	${map["m1"] }	
	<br>	
	<c:forEach items="${map }" var="m"> <!-- m这时是个entrySet对象 -->
		${m.key } &nbsp;
		${m.value } &nbsp;
	</c:forEach>
	<br>
	${strs }
	${strs[0] }<br>
	<c:forEach items="${strs }" var="s">
		${s }
	</c:forEach>
	<br>
	是否空：${empty b}
	<br>
	<c:forEach items="${objectMap }" var="obj">
		${obj.key }:${obj.value.number1 }
		<br>
	</c:forEach>
	<br>
	${pageContext.request.contextPath }
	<br>
	<!-- name是页面传值 ，或${param["name"] }-->
	${param.name }
	
	<br>
	${header.connection }
	<%
	Cookie cookie = new Cookie("user","admin");
	cookie.setMaxAge(60*60);
	cookie.setPath("/MyAlpha");
	response.addCookie(cookie);
	%>
	<br>
	${cookie.user }<br>
	${cookie.user.name }<br>
	${cookie.user.value }<br>
	${cookie.user.maxAge }<br>
	${cookie.user.path }<br>
	
	
<!-- 
*EL
页面变量
字符串
自加
数组
map
list
双引号
对象


empty
域
scope
pageContext...path
param.name
header["connection
cookit name value 

 -->



</body>
</html>