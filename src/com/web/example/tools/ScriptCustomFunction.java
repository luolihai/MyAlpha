package com.web.example.tools;

public class ScriptCustomFunction {

	/**
	 * 过滤大小于号
	 * @param str
	 * @return
	 */
	public static String filterScript(String str){
		if (str == null) return "";
		
		if (str.contains("<")) {
			str = str.replaceAll("<", "&lt;");
		}
		if (str.contains(">")) {
			str = str.replaceAll(">", "&gt;");
		}
		return str;
	}
}
