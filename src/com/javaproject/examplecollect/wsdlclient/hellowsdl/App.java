package com.javaproject.examplecollect.wsdlclient.hellowsdl;

public class App {
	/**
	 * 调用自建wsdl服务小例
	 * @param args
	 */
	public static void main(String[] args) {
		
		//看wsdl文档service 元素
		HelloWSDLServerService serverService = new HelloWSDLServerService();
		//看wsdl文档service port 元素
		HelloWSDLServer helloWSDLServerPort = serverService.getHelloWSDLServerPort();
		//看wsdl文档binding  operation 元素 ,参数看xsd:import 的导入地址内容
		String str = helloWSDLServerPort.returnMessage("张B", 1);
		System.out.println("结果："+str);
		
	}

}
