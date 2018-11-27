package com.javaproject.examplecollect.base64;

import java.io.IOException;

import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * base64编码，最大值是0~63,对应键盘码表A~Za~z0~9+/
 * @author 26031
 *
 */
public class Base64Demo {

	@Test
	public void encodeTest(){
		System.out.println(encode("木林"));
	}

	public static String encode(String name) {
		BASE64Encoder base = new BASE64Encoder();
		String nameEncode = base.encode(name.getBytes());
		return nameEncode;
	}
	
	@Test
	public void decodeTest() throws IOException{
		System.out.println(decode("5pyo5p6X","utf-8"));
	}

	public static String decode(String encode,String encodeType) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decode = decoder.decodeBuffer(encode);
		return new String(decode,encodeType);
	}

}
