<%@page import="com.web.example.domain.Calculate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计算器</title>
<style type="text/css">
	div{ 
		margin:auto;
	}
	div table {
		width: 20%;
	}
</style>
</head>
<body>
	<div>
	
		<jsp:useBean id="cal" class="com.web.example.domain.Calculate" scope="page"></jsp:useBean>
		<jsp:setProperty property="*" name="cal"/>
		<%
			try{
				cal.calculateNumber();
			}catch(RuntimeException e){
				pageContext.setAttribute("msg", e.getMessage(),PageContext.PAGE_SCOPE); 				
			}
		%>
		<form action="calculate.jsp">
			<table align="center"> 
				<tr>
					<td>number1:</td>
					<td>
						<input type="text" name="number1">
					</td>
				</tr>
				<tr>
					<td>操作:</td>
					<td>
						<select name="operater">
							<option value="+">+</option>
							<option value="-">-</option>
							<option value="*">*</option>
							<option value="/">/</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>number2:</td>
					<td>
						<input type="text" name="number2">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="计算">
					</td>
				</tr>
				<tr>
					<td>结果:</td>
					<td>
						<jsp:getProperty property="result" name="cal"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<font color="red">
						<%
							out.write((String)pageContext.getAttribute("msg",PageContext.PAGE_SCOPE));
						%>
						</font>
					</td>
				</tr>
			</table>
		</form>
					
	</div>
</body>
</html>