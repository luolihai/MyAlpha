package com.javaproject.examplecollect.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;


/**
 * MD5加密，消息摘要算法,hash计算，属单向加密，不能解密
 * @author 26031
 *
 */
public class MD5Encryption {
	
	public static String encode(String src){
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] encodeBytes = md.digest(src.getBytes());
			
			return Hex.encodeHexString(encodeBytes);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decode(String str){
		throw new RuntimeException("MD5 no decode");
	}
	
	public static void main(String[] args) {
		String result = encode("123123");
		System.out.println(result);
		//decode("4297f44b13955235245b2497399d7a93");
	}
}
