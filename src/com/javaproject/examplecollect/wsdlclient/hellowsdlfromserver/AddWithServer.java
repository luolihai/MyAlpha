package com.javaproject.examplecollect.wsdlclient.hellowsdlfromserver;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * 通过服务方式的调用，只需一个接口类，该类一般是service name如"HelloWSDLServerService"去除最后的Service的类名
 * 要注释掉报错的接口类位置
 * @author 26031
 *
 */
public class AddWithServer {

	public static void main(String[] args) throws MalformedURLException {
		String url = "http://192.168.0.3/hello?wsdl";
		//create(wsdlurl,(targetnamespaceurl,servcename))
		Service createService = Service.create(new URL(url), new QName("http://wsdl.examplecollect.javaproject.com/", "HelloWSDLServerService"));
		//getPort((targetnamespaceurl,portname),serverInterfaceClass);
		HelloWSDLServer port = createService.getPort(new QName("http://wsdl.examplecollect.javaproject.com/","HelloWSDLServerPort"), HelloWSDLServer.class);
		System.out.println(port.returnMessage("haha", 12));
		
	}
}
