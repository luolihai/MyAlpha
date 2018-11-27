<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	//ajax调用
	//创建xmlreq
	var xhr = new XMLHttpRequest();//不支持id低版本
	
	//** IE可以，谷歌与edge不行
	var requestBody = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:q0='http://WebXml.com.cn/' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
		  "<soapenv:Body>"+
	  "<q0:getMobileCodeInfo>"+
	  "<q0:mobileCode>15812841211</q0:mobileCode> "+
	  "</q0:getMobileCodeInfo>"+
	  "</soapenv:Body>"+
	  "</soapenv:Envelope>";
	
	var sendWSDL = function(){
		var url = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
		//post方式open
		xhr.open("post", url, true);
		//setHeader
		xhr.setRequestHeader("content-type", "text/xml;charset=utf-8")
		//回调函数
		xhr.onreadystatechange = callback;
		//send
		xhr.send(requestBody);
	}
	var callback = function(){
		//获取内容
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var bodyEle = document.getElementsByTagName("body")[0];
				//回显出body
				bodyEle.textContent = xhr.responseText;
			}
		}
	}

</script>
<body>
	<input type="text" id="msg">
	<input type="button" value="使用ajax调用WebService服务" onclick="sendWSDL()">
</body>
</html>