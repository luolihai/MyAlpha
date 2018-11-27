package com.web.example.common;

import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;


/**
 * 简单的Base64编码解码，使用apache的类， jdk8的话则不用，用自带
 * @author 26031
 *
 */
public class Base64EncryptionAndDecryption {
	
	public static void encodeAndDecode(String text) throws UnsupportedEncodingException{
		final Base64 base64 = new Base64();
		final byte[] textByte = text.getBytes("UTF-8");
		//编码
		final String encodedText = base64.encodeToString(textByte);
		System.out.println(encodedText);
		//解码
		System.out.println(new String(base64.decode(encodedText), "UTF-8"));
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		encodeAndDecode("123123");
	}
}
