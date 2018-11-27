package com.javaproject.examplecollect.wsdl;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
/**
 * 通过jdk发布简单的wsdl服务，该服务是通过soap简单对象访问协议，http xml数据交互，socket底层实现
 * @author 26031
 *
 */
@WebService
public class HelloWSDLServer {
	
	public String returnMessage(String msg,int num){
		String str = "接收到："+msg+"与"+num;
		System.out.println(str);
		return str;
	}
	
	public static void main(String[] args) {
		HelloWSDLServer implementor = new HelloWSDLServer();
		//指定地址，http开头,浏览器通过 http://192.168.0.3/hello?wsdl调用
		String address = "http://192.168.0.3/hello";
		Endpoint.publish(address, implementor);
		System.out.println("打印证明是多线程...");
	}

}
