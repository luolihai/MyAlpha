package com.javaproject.utilcollect.apachecommon;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

/**
	md5Hex MD5加密，返回32位
	sha1Hex SHA-1加密
	sha256Hex SHA-256加密
	sha512Hex SHA-512加密
	md5 MD5加密，返回16位
 * @author 26031
 *
 */
public class DigestUtilsTest {

	
//	md5Hex MD5加密，返回32位
	public static void md5HexPeek(){
		String str = DigestUtils.md5Hex("abc".getBytes());
		System.out.println(str);
	}
//	sha1Hex SHA-1加密
	public static void sha1Hex(){
		String str = DigestUtils.sha1Hex("abc".getBytes());
		System.out.println(str);
		System.out.println(str.length());
	}
	
//	sha256Hex SHA-256加密
	public static void sha256HexPeek(){
		String str = DigestUtils.sha256Hex("abc");
		System.out.println(str);
		System.out.println(str.length());
	}
	
//	sha512Hex SHA-512加密
	public static void sha512HexPeek(){
		String str = DigestUtils.sha512Hex("abc");
		System.out.println(str);
		System.out.println(str.length());
	}
//	md5 MD5加密，返回16位
	public static void md5Peek() throws UnsupportedEncodingException{
		byte[] bs = DigestUtils.md5("abc".getBytes("iso-8859-1"));
		String str = new String(bs,"iso-8859-1");
		System.out.println(str);
		System.out.println(str.length());
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		md5Peek();
	}
}
