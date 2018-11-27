package com.javaproject.examplecollect.wsdlclient.myhellowsdlfromserver;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class MyAppWithServer {

	public static void main(String[] args) throws MalformedURLException {
		//url
		String url = "http://192.168.0.3/myHello?wsdl";
		//targetNamespace
		String namespaceurl = "http://abc.com/";
		//service name
		Service createServcie = Service.create(new URL(url), new QName(namespaceurl, "myWSDLServer"));
		//HelloWSDLModifyServer是接口类，port name
		HelloWSDLModifyServer portService = createServcie.getPort(new QName(namespaceurl, "myWSDLServerPort"), HelloWSDLModifyServer.class);
		//调用
		System.out.println(portService.getMsg("aaa", 22));
		
	}
}
