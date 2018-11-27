package com.web.example.servlet.struts2;

import org.junit.Test;

public class ServletUtil {

	public static String parse(String url){
		String[] split = url.split("/");
		String prefix = split[split.length-1].substring(0,split[split.length-1].lastIndexOf("."));
		System.out.println("截取："+prefix);
		return prefix;
	}
	
	@Test
	public void test(){
		String url = "http://localhost:8088/MyAlpha/mult/exampleJsp/struts2/UserAction.action";
		ServletUtil.parse(url);
	}
	
}
