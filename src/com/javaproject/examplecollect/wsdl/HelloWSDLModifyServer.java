package com.javaproject.examplecollect.wsdl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
/**
 * 通过jdk发布简单的wsdl服务，该服务是通过soap简单对象访问协议，http xml数据交互，socket底层实现
 * @author 26031
 *
 */
@WebService(targetNamespace="http://abc.com/",serviceName="myWSDLServer",portName="myWSDLServerPort")
public class HelloWSDLModifyServer {
	
	@WebMethod(operationName="getMsg")
	@WebResult(name="myReturn")
	public String returnMessage(
			@WebParam(name="myMsg")String msg,
			@WebParam(name="myNum")
			int num){
		String str = "接收到："+msg+"与"+num;
		System.out.println(str);
		return str;
	}
	
	public static void main(String[] args) {
		HelloWSDLModifyServer implementor = new HelloWSDLModifyServer();
		//指定地址，http开头,浏览器通过 http://192.168.0.3/hello?wsdl调用
		String address = "http://192.168.0.3/myHello";
		Endpoint.publish(address, implementor);
		System.out.println("打印证明是多线程...");
	}

}
