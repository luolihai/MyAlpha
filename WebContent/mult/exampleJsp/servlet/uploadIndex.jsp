<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/uploadServlet2" method="post" enctype="multipart/form-data">
		名称：<input type="text" name="username"><br>
		文件：<input type="file" name="multipartFile" ><br>
		文件：<input type="file" name="multipartFile" ><br>
		名称：<input type="submit" value="提交" ><br>
	</form>
</body>
</html>