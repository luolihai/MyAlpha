package com.javaproject.examplecollect.wsdlclient.airlinewsdl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.net.www.content.audio.aiff;

import com.javaproject.examplecollect.wsdlclient.airlinewsdl.GetDomesticAirlinesTimeResponse.GetDomesticAirlinesTimeResult;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
/**
 * 航班wsdl信息
 * @author 26031
 *
 */
public class AirlineApp {

	public static void main(String[] args) throws MalformedURLException {
		
		
		/*
		DomesticAirline airline = new DomesticAirline();
		DomesticAirlineSoap domesticAirlineSoap = airline.getDomesticAirlineSoap();
		GetDomesticAirlinesTimeResult airObject = domesticAirlineSoap.getDomesticAirlinesTime("广州", "上海", "", "");
		Object any = airObject.getAny();
		System.out.println(any.getClass());
		*/
		
		
		String url = "http://ws.webxml.com.cn/webservices/DomesticAirline.asmx?wsdl";
		String targetUrl = "http://WebXml.com.cn/";
		Service createService = Service.create(new URL(url), new QName(targetUrl, "DomesticAirline"));
		DomesticAirlineSoap portService = createService.getPort(new QName(targetUrl, "DomesticAirlineSoap"), DomesticAirlineSoap.class);
		GetDomesticAirlinesTimeResult domesticAirlinesTime = portService.getDomesticAirlinesTime("广州", "上海", "", "");
		
		ElementNSImpl any = (ElementNSImpl)domesticAirlinesTime.getAny();
		NodeList airlines = any.getElementsByTagName("AirlinesTime");
		//遍历航班信息
		for (int i = 0; i < airlines.getLength(); i++) {
			Node item = airlines.item(i);
			Node firstChild = item.getFirstChild();
			System.out.println(firstChild.getTextContent());
		}
		
		
		
	}
}
