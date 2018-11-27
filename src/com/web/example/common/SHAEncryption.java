package com.web.example.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;


/**
 * SHA加密，消息摘要算法,安全散列计算，属单向加密，不能解密
 * @author 26031
 *
 */
public class SHAEncryption {
	public static String encode(String src){
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(src.getBytes());
			return Hex.encodeHexString(md.digest());
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decode(String src){
		throw new RuntimeException("SHA no decode");
	}
	
	public static void main(String[] args) {
		String result = encode("123123");
		System.out.println(result);		//有40的长度呀
		//decode("601f1889667efaebb33b8c12572835da3f027f78");
	}
}
