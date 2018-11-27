package com.javaproject.examplecollect.wsdlclient.weathwsdl;

import java.util.List;

public class App {
	
	public static void main(String[] args) {
		
		WeatherWS weatherWS = new WeatherWS();
		WeatherWSSoap weatherWSSoap = weatherWS.getWeatherWSSoap();
		ArrayOfString weather = weatherWSSoap.getWeather("广州", null);
		List<String> strs = weather.getString();
		for (int i = 0; i < strs.size(); i++) {
			System.out.println(strs.get(i));
		}
		
	}

}
