package com.javaproject.examplecollect.wsdlclient.urlclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import sun.net.www.protocol.http.HttpURLConnection;

/**
 * url 服务类型
 * @author 26031
 *
 */
public class URLWSDLClient {

	public static void main(String[] args) throws IOException {
		
		//地址
		URL url = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
		
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		
		httpConn.setDoInput(true);//有输入
		httpConn.setDoOutput(true);//有输出
		httpConn.setRequestProperty("Content-type", "text/xml;charset=utf-8");
		httpConn.setRequestMethod("POST");
		
		String soap  = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:q0='http://WebXml.com.cn/' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"+
				  "<soapenv:Body>"+
			  "<q0:getMobileCodeInfo>"+
			  "<q0:mobileCode>15812841211</q0:mobileCode> "+
			  "</q0:getMobileCodeInfo>"+
			  "</soapenv:Body>"+
			  "</soapenv:Envelope>";
		
		OutputStream outputStream = httpConn.getOutputStream();
		outputStream.write(soap.getBytes());
		
		InputStream inputStream = httpConn.getInputStream();
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[1024];
		int end = 0;
		while ((end = inputStream.read(b)) != -1) {
			sb.append(new String(b, 0, end));
		}
		System.out.println(sb.toString());
		
		inputStream.close();
		outputStream.close();
		httpConn.disconnect();
	}
}
