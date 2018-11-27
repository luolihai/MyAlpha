<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义函数标签</title>
</head>
<body>
	<%@ taglib uri="/WEB-INF/customFunction.tld" prefix="custom" %>
	自定义script过滤：
	${custom:filterscript("<script>alert();</script>") }
	<br>
	四舍五入：
	${custom:round(125.5363) }
	
</body>
</html>