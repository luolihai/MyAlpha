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
	<%@ taglib uri="/WEB-INF/helloCustmoTag.tld" prefix="jpn"%>
	<jpn:date pattern="yyyy年MM月dd日   HH时mm分ss秒"/>
	
	<br>
	<jpn:choose>
		<jpn:when test="1>2">
			1大于2
		</jpn:when>
		<jpn:otherwise>
			1小于2
		</jpn:otherwise>
	</jpn:choose>

</body>
</html>