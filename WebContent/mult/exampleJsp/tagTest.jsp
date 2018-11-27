<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标签库使用</title>
</head>
<body>
	<c:if test="<%=1/2>1 %>">
		1/2>1大于1	<br>
	</c:if>
	<c:if test="<%=1/2<1 %>">
		1/2>1小于1	<br>
	</c:if>
	
	<c:choose>
		<c:when test="<%=1/2>1 %>">
			is choose when:1/2>1大于1
		</c:when>
		<c:otherwise>
			is otherwise:1/2>1小于1
		</c:otherwise>
	</c:choose>
	
	<br>
	<c:url value="calculate.jsp" var="calc">
		<c:param name="number1" value="12"></c:param>
		<c:param name="number2" value="5"></c:param>
		<c:param name="operater" value="+"></c:param>
	</c:url>
	<a href='<%=pageContext.getAttribute("calc",PageContext.PAGE_SCOPE)%>'>计算器</a>
	<br>
	
	<%-- 要加上String强转
	<c:redirect url='<%=(String)pageContext.getAttribute("calc",PageContext.PAGE_SCOPE)%>'>
	</c:redirect>
	 --%>
	
	<c:forEach  begin="0" end="10" var="i">
		<%=pageContext.getAttribute("i",PageContext.PAGE_SCOPE) %>
	</c:forEach>
	<br>
	<%
		ArrayList<String> list = new ArrayList<String>();
		list.add("jack");
		list.add("lucy");
		list.add("lili");
	%>
	<c:forEach items='<%=list %>' var="name" varStatus="status">
		${status.index }&nbsp;
		${status.count }&nbsp;
		${status.first }&nbsp;
		${status.last }
		<%=pageContext.getAttribute("name",PageContext.PAGE_SCOPE) %><br>
	</c:forEach>
	<br><br>
	<c:forTokens items="jack,lucy,lili" delims="," var="name" step="2" varStatus="status"> 
		${status.count }&nbsp;
		<%=pageContext.getAttribute("name",PageContext.PAGE_SCOPE) %><br>
	</c:forTokens>
	
	<br>
	<!-- 	动态导入 -->
	<c:import url='<%=(String)pageContext.getAttribute("calc",PageContext.PAGE_SCOPE)%>'>
	</c:import>
	
	
</body>
</html>