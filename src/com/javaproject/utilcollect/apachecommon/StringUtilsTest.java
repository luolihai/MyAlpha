package com.javaproject.utilcollect.apachecommon;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

/**
	isBlank 字符串是否为空 (trim后判断)
	isEmpty 字符串是否为空 (不trim并判断)
	equals 字符串是否相等,可以比较空值
	join 合并数组为单一字符串，可传分隔符
	split 分割字符串
	trimToNull trim后为空字符串则转换为null
	replace 替换字符串
	capitalize 首字符大写 
 * @author 26031
 *
 */
public class StringUtilsTest {

//	isBlank 字符串是否为空 (trim后判断)
	public static void isBlankPeek(){
		System.out.println("不用说，用过了");
	}
	
//	isEmpty 字符串是否为空 (不trim并判断)
	public static void isEmptyPeek(){
		System.out.println(StringUtils.isEmpty(""));
		System.out.println(StringUtils.isEmpty(" "));
		System.out.println(StringUtils.isEmpty(null));
	}
	
//	equals 字符串是否相等，可以比较空值
	public static void equalsPeek(){
		System.out.println(StringUtils.equals("", "1"));
		System.out.println(StringUtils.equals(null, null));
		System.out.println(StringUtils.equals("", null));
	}
	
//	join 合并数组为单一字符串，可传分隔符,连接byte[]有点怪怪
	public static void joinPeek(){
		System.out.println(StringUtils.join("1","2","3"));
		System.out.println(StringUtils.join(new String[]{"22","33"},"--"));
		System.out.println(StringUtils.join(new byte[]{1,2,3},"--"));
	}
	
//	split 分割字符串
	public static void splitPeek(){
		System.out.println(StringUtils.split("aa2bb2cc2"));
		System.out.println(StringUtils.split("aa2bb2cc2","2"));
	}
//	trimToNull trim后为空字符串则转换为null
	public static void trimToNullPeek(){
		System.out.println(StringUtils.trimToNull("  "));
		System.out.println(StringUtils.trimToNull(""));
		System.out.println(StringUtils.trimToNull(null));
	}
	
//	replace 替换字符串
	public static void replacePeek(){
		System.out.println(StringUtils.replace("aa2kk2kk2jj2nn", "2", "-"));
	}
	
//	capitalize 首字符大写 
	public static void capitalizePeek(){
		System.out.println(StringUtils.capitalize("abcsf"));
		System.out.println(StringUtils.uncapitalize("ABCSF"));
	}
	
	public static void main(String[] args) {
		capitalizePeek();
	}
	
}
